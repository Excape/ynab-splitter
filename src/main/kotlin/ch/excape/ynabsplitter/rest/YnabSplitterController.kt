package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.adapter.persistence.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.rest.RestApproveTransactionPresenter
import ch.excape.ynabsplitter.adapter.rest.RestCategoryListPresenter
import ch.excape.ynabsplitter.adapter.rest.RestMatchedTransactionPresenter
import ch.excape.ynabsplitter.adapter.rest.RestTransactionsListPresenter
import ch.excape.ynabsplitter.adapter.rest.document.CategoryDocument
import ch.excape.ynabsplitter.adapter.rest.document.MatchedTransactionDocument
import ch.excape.ynabsplitter.adapter.rest.document.SplitTransactionRequest
import ch.excape.ynabsplitter.adapter.rest.document.UnapprovedTransactionDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
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
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.TransactionSplit
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class YnabSplitterController(
        private val readCategoriesRepository: ReadCategoriesRepository,
        private val readTransactionRepository: ReadTransactionsRepository,
        private val saveTransactionRepository: SaveTransactionRepository
) {


    @GetMapping("/transactions")
    fun getTransactions(): List<UnapprovedTransactionDocument> {
        val listUnapprovedTransactions: IListUnapprovedTransactions = ListUnapprovedTransactions(readTransactionRepository)
        val transactionPresenter = RestTransactionsListPresenter()

        val input = ListUnapprovedTransactionsInput(listOf(Actor.ROBIN, Actor.SOPHIE))

        listUnapprovedTransactions.executeWith(input, transactionPresenter)

        return transactionPresenter.presentation!!
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
            @PathVariable("id") transactionId: String,
            @RequestParam("categoryId") categoryId: String,
            @RequestParam("from") fromActor: Actor,
            @RequestParam("for") forActor: Actor
    ): ApproveTransactionResult {

        val matchedTransaction = executeGetMatchedTransaction(transactionId)
        return executeApproveSingleTransaction(matchedTransaction, fromActor, forActor, categoryId)

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
        val approveTransaction: IApproveTransaction = ApproveTransaction(saveTransactionRepository, InMemoryAuditLogRepository())

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }

    @PostMapping("/transactions/{id}/approveSplit")
    fun approveSplitTransaction(
            @PathVariable("id") transactionId: String,
            @RequestParam("from") fromActor: Actor,
            @RequestBody(required = true) splitRequest: SplitTransactionRequest
    ): ApproveTransactionResult {

        val matchedTransaction = executeGetMatchedTransaction(transactionId)
        return executeApproveSplitTransaction(matchedTransaction, fromActor, splitRequest)

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

        val approveTransaction: IApproveTransaction = ApproveTransaction(saveTransactionRepository, InMemoryAuditLogRepository())

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }
}


