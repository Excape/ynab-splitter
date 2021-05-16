package ch.excape.ynabsplitter.rest.controller

import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.RestAllAuditLogsPresenter
import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.RestSingleAuditLogPresenter
import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.AuditLogDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.*
import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.*
import ch.excape.ynabsplitter.adapter.presentation.rest.user.RestUserPresenter
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SplitterActorDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.UserDocument
import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.auditlog.get_auditlog.GetAuditLog
import ch.excape.ynabsplitter.application.use_cases.auditlog.list_auditlog.ListAuditLog
import ch.excape.ynabsplitter.application.use_cases.auditlog.list_auditlog.ports.IListAuditLog
import ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports.MatchedTransactionIds
import ch.excape.ynabsplitter.application.use_cases.transaction.get_categories.GetCategories
import ch.excape.ynabsplitter.application.use_cases.transaction.get_categories.ports.GetCategoriesInput
import ch.excape.ynabsplitter.application.use_cases.transaction.get_categories.ports.IGetCategories
import ch.excape.ynabsplitter.application.use_cases.transaction.get_matched_transaction.GetMatchedTransaction
import ch.excape.ynabsplitter.application.use_cases.transaction.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.application.use_cases.transaction.get_matched_transaction.ports.IGetMatchedTransaction
import ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions.ListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.UndoApproval
import ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.ports.UndoApprovalInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.GetUser
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.GetUserInput
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.TransactionSplit
import ch.excape.ynabsplitter.rest.toDomain
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1")
class YnabSplitterController(
    private val readCategoriesRepository: ReadCategoriesRepository,
    private val readTransactionRepository: ReadTransactionsRepository,
    private val saveTransactionRepository: SaveTransactionRepository,
    private val auditLogRepository: AuditLogRepository,
    private val userRepository: UserRepository
) {
    @GetMapping("/transactions")
    fun getTransactions(principal: Principal): List<UnapprovedTransactionDocument> {

        val loggedInUser = getLoggedInUser(principal)

        val listUnapprovedTransactions: IListUnapprovedTransactions = ListUnapprovedTransactions(
            readTransactionRepository, userRepository
        )
        val transactionPresenter = RestTransactionsListPresenter()

        val input = ListUnapprovedTransactionsInput(loggedInUser.userId)

        listUnapprovedTransactions.executeWith(input, transactionPresenter)

        return transactionPresenter.presentation!!
    }

    @GetMapping("/actors")
    fun getActors(principal: Principal): List<SplitterActorDocument> {
        val loggedInUser = getLoggedInUser(principal)
        return loggedInUser.settings.actors
    }

    private fun getLoggedInUser(principal: Principal): UserDocument {
        val input = GetUserInput(userId = principal.name)
        val presenter = RestUserPresenter()
        GetUser(userRepository).executeWith(input, presenter)
        return presenter.presentation!!
    }

    @GetMapping("/auditlog")
    fun getAuditLog(): List<AuditLogDocument> {
        val auditLogPresenter = RestAllAuditLogsPresenter()
        val listAuditLogs: IListAuditLog = ListAuditLog(auditLogRepository)

        listAuditLogs.executeWith(auditLogPresenter)
        return auditLogPresenter.presentation!!
    }

    fun getSingleAuditLog(auditLogId: String): AuditLogDocument {
        val presenter = RestSingleAuditLogPresenter()
        GetAuditLog(auditLogRepository).executeWith(auditLogId, presenter)

        return presenter.presentation!!
    }

    @GetMapping("/categories/{actor}")
    fun getCategories(@PathVariable("actor") actor: String, principal: Principal): List<CategoryDocument> {
        val loggedInUser = getLoggedInUser(principal)
        val getCategories: IGetCategories = GetCategories(readCategoriesRepository, userRepository)

        val presenter = RestCategoryListPresenter()
        getCategories.executeWith(GetCategoriesInput(loggedInUser.userId, actor), presenter)

        return presenter.presentation!!
    }

    @PostMapping("/transactions/{id}/approveSingle")
    fun approveSingleTransaction(
        @PathVariable("id") transactionId: String,
        @RequestBody request: SingleApprovalRequest,
        principal: Principal
    ): ApproveTransactionResultDocument {

        val loggedInUser = getLoggedInUser(principal)

        val matchedTransaction = executeGetMatchedTransaction(loggedInUser.userId, transactionId)
        val approvalResult = executeApproveSingleTransaction(matchedTransaction, loggedInUser, request)
        return attachAuditLog(approvalResult)
    }

    private fun attachAuditLog(approvalResult: ApproveTransactionResult): ApproveTransactionResultDocument {
        var auditLog: AuditLogDocument? = null;
        if (approvalResult.success) {
            auditLog = getSingleAuditLog(approvalResult.auditLogId!!)
        }
        return ApproveTransactionResultDocument(approvalResult.success, auditLog)
    }

    private fun executeGetMatchedTransaction(userId: String, transactionId: String): MatchedTransactionDocument {
        val getMatchedTransaction: IGetMatchedTransaction =
            GetMatchedTransaction(readTransactionRepository, userRepository)
        val getTransactionPresenter = RestMatchedTransactionPresenter()
        getMatchedTransaction.executeWith(GetMatchedTransactionInput(userId, transactionId), getTransactionPresenter)

        return getTransactionPresenter.presentation!!
    }

    private fun executeApproveSingleTransaction(
        matchedTransactionDocument: MatchedTransactionDocument, user: UserDocument,
        request: SingleApprovalRequest
    ): ApproveTransactionResult {

        val presenter = RestApproveTransactionPresenter()

        val forActor = request.actor
        val input = ApproveTransactionInput(
            user.userId,
            extractTransactionIds(matchedTransactionDocument),
            request.executingActor,
            TransactionSplit.allOnOne(forActor, user.settings.actors.map { it.name }),
            CategoryPerActor(forActor to Category(request.categoryId))
        )
        val approveTransaction: IApproveTransaction = ApproveTransaction(
            readTransactionRepository, saveTransactionRepository,
            readCategoriesRepository, auditLogRepository, userRepository
        )

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }

    private fun extractTransactionIds(matchedTransaction: MatchedTransactionDocument) =
        MatchedTransactionIds(matchedTransaction.transactions.associate { it.actor.name to it.id })

    @PostMapping("/transactions/{id}/approveSplit")
    fun approveSplitTransaction(
        request: HttpServletRequest,
        @PathVariable("id") transactionId: String,
        @RequestBody(required = true) splitRequest: SplitTransactionRequest,
        principal: Principal
    ): ApproveTransactionResultDocument {

        val loggedInUser = getLoggedInUser(principal)

        val matchedTransaction = executeGetMatchedTransaction(loggedInUser.userId, transactionId)
        val approvalResult = executeApproveSplitTransaction(matchedTransaction, loggedInUser.userId, splitRequest)
        return attachAuditLog(approvalResult)
    }

    private fun executeApproveSplitTransaction(
        matchedTransactionDocument: MatchedTransactionDocument, userId: String,
        splitRequest: SplitTransactionRequest
    ): ApproveTransactionResult {
        val presenter = RestApproveTransactionPresenter()
        val input = ApproveTransactionInput(
            userId,
            extractTransactionIds(matchedTransactionDocument),
            splitRequest.executingActor,
            splitRequest.split.toDomain(),
            splitRequest.categories.toDomain()
        )

        val approveTransaction: IApproveTransaction = ApproveTransaction(
            readTransactionRepository,
            saveTransactionRepository, readCategoriesRepository, auditLogRepository, userRepository
        )

        approveTransaction.executeWith(input, presenter)
        return presenter.presentation!!
    }

    @PostMapping("/auditlog")
    fun undoApproval(
        @RequestBody(required = true) request: UndoApprovalRequest,
        principal: Principal
    ): UndoApprovalResultDocument {

        val loggedInUser = getLoggedInUser(principal)
        val presenter = RestUndoneApprovalPresenter()
        val undoApproval = UndoApproval(auditLogRepository, saveTransactionRepository)

        val input = UndoApprovalInput(loggedInUser.userId, request.auditLogId)
        undoApproval.executeWith(input, presenter)

        return presenter.presentation!!
    }
}


