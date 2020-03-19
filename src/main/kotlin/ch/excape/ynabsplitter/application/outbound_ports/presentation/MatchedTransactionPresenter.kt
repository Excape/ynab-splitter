package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.MatchedTransaction

interface MatchedTransactionPresenter {
    fun present(matchedTransaction: MatchedTransaction?)
}
