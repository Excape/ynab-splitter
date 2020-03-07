package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.transactions.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Transaction

class ListUnapprovedTransactions(private val readTransactionsRepository: ReadTransactionsRepository)
    : IListUnapprovedTransactions {

    override fun executeWith(input: ListUnapprovedTransactionsInput, presenter: TransactionListPresenter) {
        val allTransactions = input.actors
                .map { readTransactions(it) }
                .flatten()

        val matchedTransactions = matchTransactions(allTransactions)

        presenter.present(matchedTransactions)
    }

    private fun readTransactions(actor: Actor): List<Transaction> {
        return readTransactionsRepository.getAllTransactionsFromLastWeek(actor)
    }
}
