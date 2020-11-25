package ch.excape.ynabsplitter.application.use_cases.approve_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.domain.*
import java.time.LocalDateTime
import kotlin.math.roundToLong

class ApproveTransaction(
        private val readTransactionsRepository: ReadTransactionsRepository,
        private val saveTransactionRepository: SaveTransactionRepository,
        private val categoriesRepository: ReadCategoriesRepository,
        private val auditLogRepository: AuditLogRepository,
        private val userRepository: UserRepository
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
        val actors = getActors(input.userId)
        val transactions = loadTransactions(input.transaction.transactionIdsByActor, actors)
        for (transaction in transactions) {

            val split = splitTransaction(transaction, input.split)
            val category = getCategory(transaction, input.categories)
            val approvedTransaction = createApprovedTransaction(transaction, split, category)

            saveTransactionRepository.saveTransaction(approvedTransaction)

            val auditLog = AuditLog(LocalDateTime.now(), transaction, approvedTransaction, input.executingActor)
            auditLogRepository.saveAuditLog(auditLog)
        }
    }

    private fun loadTransactions(transactionIdsByActor: Map<String, String>, actors: List<SplitterActor>): List<Transaction> {
        return transactionIdsByActor.map { entry ->
            readTransactionsRepository.getTransaction(actors.getByName(entry.key), entry.value)
                    ?: throw IllegalArgumentException("transaction ${entry.value} not found")
        }
    }

    private fun getActors(userId: String): List<SplitterActor> {
        val user = userRepository.getUser(userId) ?: throw IllegalArgumentException("User with id $userId not found")
        return user.settings.actors
    }

    private fun getCategory(transaction: Transaction, categories: CategoryPerActor): Category? {
        val inputCategory = categories[transaction.actor.name] ?: transaction.category
        return if (inputCategory != null) {
            categoriesRepository.findCategory(transaction.actor, inputCategory.id)
        } else null
    }

    private fun splitTransaction(transaction: Transaction, splits: TransactionSplit): Long {
        val split: Double = splits[transaction.actor.name]
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