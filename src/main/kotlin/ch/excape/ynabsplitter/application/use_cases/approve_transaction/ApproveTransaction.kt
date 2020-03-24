package ch.excape.ynabsplitter.application.use_cases.approve_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.domain.AuditLog
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.TransactionSplit
import kotlin.math.roundToLong

class ApproveTransaction(
        private val saveTransactionRepository: SaveTransactionRepository,
        private val auditLogRepository: AuditLogRepository
) : IApproveTransaction {

    override fun executeWith(input: ApproveTransactionInput, presenter: ApproveTransactionPresenter) {
        try {
            approveTransactions(input)
            presenter.present(ApproveTransactionResult(true))
        } catch (ex: Exception) {
            presenter.present(ApproveTransactionResult(false))
            println("Exception in ApproveTransaction use case: $ex")
            ex.printStackTrace()
        }
    }

    private fun approveTransactions(input: ApproveTransactionInput) {
        val transactions = input.transaction.transactions
        for (transaction in transactions) {

            val split = splitTransaction(transaction, input.split)
            val category = getCategory(transaction, input.categories)
            val approvedTransaction = createApprovedTransaction(transaction, split, category)

            saveTransactionRepository.saveTransaction(approvedTransaction)

            val auditLog = AuditLog(transaction, approvedTransaction, input.executingActor)
            auditLogRepository.saveAuditLog(auditLog)
        }
    }

    private fun getCategory(transaction: Transaction, categories: CategoryPerActor) =
            categories[transaction.actor] ?: transaction.category

    private fun splitTransaction(transaction: Transaction, splits: TransactionSplit): Long {
        val split: Double = splits[transaction.actor]
                ?: throw IllegalArgumentException("No split defined for actor ${transaction.actor}")
        return (split * transaction.amount).roundToLong()
    }

    private fun createApprovedTransaction(transaction: Transaction, split: Long, category: Category?): Transaction {
        var memo = transaction.memo
        if (split == 0L) {
            memo = "You can delete me!"
        }
        return transaction.copy(isApproved = true, amount = split, category = category, memo = memo)
    }
}