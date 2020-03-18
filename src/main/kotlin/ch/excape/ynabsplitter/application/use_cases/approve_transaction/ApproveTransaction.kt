package ch.excape.ynabsplitter.application.use_cases.approve_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.domain.AuditLog
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.TransactionSplit
import kotlin.math.roundToLong

class ApproveTransaction(
        private val saveTransactionRepository: SaveTransactionRepository,
        private val auditLogRepository: AuditLogRepository
) : IApproveTransaction {

    override fun executeWith(input: ApproveTransactionInput, presenter: ApproveTransactionPresenter) {
        val transactions = input.transaction.transactions
        
        for (transaction in transactions) {

            val split = splitTransaction(transaction, input.split)
            val approvedTransaction = createApprovedTransaction(transaction, split)

            saveTransactionRepository.saveTransaction(approvedTransaction)

            val auditLog = AuditLog(transaction, approvedTransaction, input.executingActor)
            auditLogRepository.saveAuditLog(auditLog)
        }
    }

    private fun splitTransaction(transaction: Transaction, splits: TransactionSplit): Long {
        if (!splits.containsKey(transaction.actor)) {
            throw IllegalArgumentException("No split defined for actor ${transaction.actor}")
        }
        val split: Double = splits[transaction.actor!!]!!
        return (split * transaction.amount).roundToLong()
    }

    private fun createApprovedTransaction(transaction: Transaction, split: Long): Transaction {
        return transaction.copy(isApproved = true, amount = split)
    }
}