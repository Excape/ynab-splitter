package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.adapter.rest.*
import ch.excape.ynabsplitter.adapter.rest.document.*
import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AuditLogPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.get_categories.GetCategories
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.GetCategoriesInput
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.IGetCategories
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.GetMatchedTransaction
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.IGetMatchedTransaction
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ListAuditLog
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListAuditLog
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.TransactionSplit
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1")
class YnabSplitterController(
        private val readCategoriesRepository: ReadCategoriesRepository,
        private val readTransactionRepository: ReadTransactionsRepository,
        private val saveTransactionRepository: SaveTransactionRepository,
        private val auditLogRepository: AuditLogRepository,
        private val loginManager: LoginManager
) {
    @GetMapping("/transactions")
    fun getTransactions(): List<UnapprovedTransactionDocument> {
        val listUnapprovedTransactions: IListUnapprovedTransactions = ListUnapprovedTransactions(readTransactionRepository)
        val transactionPresenter = RestTransactionsListPresenter()

        val input = ListUnapprovedTransactionsInput(listOf(Actor.ROBIN, Actor.SOPHIE))

        listUnapprovedTransactions.executeWith(input, transactionPresenter)

        return transactionPresenter.presentation!!
    }

    @GetMapping("/auditlog")
    fun getAuditLog(): List<AuditLogDocument> {
        val auditLogPresenter = RestAuditLogPresenter()
        val listAuditLogs: IListAuditLog = ListAuditLog(auditLogRepository)

        listAuditLogs.executeWith(auditLogPresenter)
        return auditLogPresenter.presentation!!
    }

    @GetMapping("/categories/{actor}")
    fun getCategories(@PathVariable("actor") actor: Actor): List<CategoryDocument> {
        val getCategories: IGetCategories = GetCategories(readCategoriesRepository)

        val presenter = RestCategoryListPresenter()
        getCategories.executeWith(GetCategoriesInput(actor), presenter)

        return presenter.presentation!!
    }

    @GetMapping("/transactions/{id}/approveSingle")
    fun approveSingleTransaction(
            request: HttpServletRequest,
            @PathVariable("id") transactionId: String,
            @RequestParam("categoryId") categoryId: String,
            @RequestParam("for") forActor: Actor
    ): ApproveTransactionResult {

        val loggedInActor = loginManager.getLoggedInActor(request)

        val matchedTransaction = executeGetMatchedTransaction(transactionId)
        return executeApproveSingleTransaction(matchedTransaction, loggedInActor, forActor, categoryId)

    }

    private fun executeGetMatchedTransaction(transactionId: String): MatchedTransactionDocument {
        val getMatchedTransaction: IGetMatchedTransaction = GetMatchedTransaction(readTransactionRepository)
        val getTransactionPresenter = RestMatchedTransactionPresenter()
        getMatchedTransaction.executeWith(GetMatchedTransactionInput(transactionId), getTransactionPresenter)

        return getTransactionPresenter.presentation!!
    }

    private fun executeApproveSingleTransaction(
            matchedTransactionDocument: MatchedTransactionDocument,
            fromActor: Actor, forActor: Actor, categoryId: String) : ApproveTransactionResult {

        val matchedTransaction = matchedTransactionDocument.toDomain()
        val presenter = RestApproveTransactionPresenter()
        val input = ApproveTransactionInput(
                matchedTransaction,
                fromActor,
                TransactionSplit.allOnOne(forActor),
                CategoryPerActor(forActor to Category(categoryId)))
        val approveTransaction: IApproveTransaction = ApproveTransaction(saveTransactionRepository, auditLogRepository)

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }

    @PostMapping("/transactions/{id}/approveSplit")
    fun approveSplitTransaction(
            request: HttpServletRequest,
            @PathVariable("id") transactionId: String,
            @RequestBody(required = true) splitRequest: SplitTransactionRequest
    ): ApproveTransactionResult {

        val loggedInActor = loginManager.getLoggedInActor(request)
        val matchedTransaction = executeGetMatchedTransaction(transactionId)
        return executeApproveSplitTransaction(matchedTransaction, loggedInActor, splitRequest)

    }

    private fun executeApproveSplitTransaction(
            matchedTransactionDocument: MatchedTransactionDocument,
            fromActor: Actor,
            splitRequest: SplitTransactionRequest
    ): ApproveTransactionResult {
        val matchedTransaction = matchedTransactionDocument.toDomain()
        val presenter = RestApproveTransactionPresenter()
        val input = ApproveTransactionInput(
                matchedTransaction,
                fromActor,
                splitRequest.split.toDomain(),
                splitRequest.categories.toDomain()
        )

        val approveTransaction: IApproveTransaction = ApproveTransaction(saveTransactionRepository, auditLogRepository)

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }
}


