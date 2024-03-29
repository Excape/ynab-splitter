package ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.transaction.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ListUnapprovedTransactions(
        private val readTransactionsRepository: ReadTransactionsRepository,
        private val userRepository: UserRepository)
    : IListUnapprovedTransactions {

    companion object {
        val log: Logger = LogManager.getLogger()
    }

    override fun executeWith(input: ListUnapprovedTransactionsInput, presenter: TransactionListPresenter) {

        val user = getUser(input.userId)
        triggerYnabImport(user)

        val allTransactions = user.settings.actors
                .map { readTransactions(it) }
                .flatten()

        val matchedTransactions = matchTransactions(allTransactions)

        val validTransactions = matchedTransactions.filter { it.transactions.size == 2 }
        val unmatchedTransactions = matchedTransactions.filter { it.transactions.size < 2 }
        val overmatchedTransactions = matchedTransactions.filter { it.transactions.size > 2 }

        unmatchedTransactions.forEach { log.debug("Unmatched transaction: $it") }
        overmatchedTransactions.forEach { log.debug("Overmatched transaction: $it") }

        presenter.present(validTransactions)
    }

    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("user with id $userId not found")

    private fun triggerYnabImport(user: User) {
        user.settings.actors.forEach {
            readTransactionsRepository.triggerTransactionImport(it)
        }
    }

    private fun readTransactions(actor: SplitterActor): List<Transaction> {
        return readTransactionsRepository.getUnapprovedTransactionsFromLastMonth(actor)
    }
}
