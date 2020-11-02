package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Transaction

class ListUnapprovedTransactions(private val readTransactionsRepository: ReadTransactionsRepository)
    : IListUnapprovedTransactions {

    override fun executeWith(input: ListUnapprovedTransactionsInput, presenter: TransactionListPresenter) {
        triggerYnabImport(input.actors);

        val allTransactions = input.actors
                .map { readTransactions(it) }
                .flatten()

        val matchedTransactions = matchTransactions(allTransactions)

        val validTransactions = matchedTransactions.filter { it.transactions.size == 2 }
        val unmatchedTransactions = matchedTransactions.filter {it.transactions.size < 2}
        val overmatchedTransactions = matchedTransactions.filter { it.transactions.size > 2 }

        unmatchedTransactions.forEach { println("Unmatched transaction: $it") }
        overmatchedTransactions.forEach { println("Overmatched transaction: $it") }

        presenter.present(validTransactions)
    }

    private fun triggerYnabImport(actors: List<Actor>) {
        actors.forEach {
            readTransactionsRepository.triggerTransactionImport(it)
        }
    }

    private fun readTransactions(actor: Actor): List<Transaction> {
        return readTransactionsRepository.getUnapprovedTransactionsFromLastMonth(actor)
    }
}
