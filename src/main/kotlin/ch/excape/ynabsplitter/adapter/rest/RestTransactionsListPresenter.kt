package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.domain.MatchedTransaction

class RestTransactionsListPresenter: TransactionListPresenter {

    var presentation: List<MatchedTransaction>? = null

    override fun present(transactions: List<MatchedTransaction>) {
        presentation = transactions
    }

}

