package ch.excape.ynabsplitter.application.use_cases.list_transactions.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter

interface IListUnapprovedTransactions {
    fun executeWith(input: ListUnapprovedTransactionsInput, presenter: TransactionListPresenter)
}
