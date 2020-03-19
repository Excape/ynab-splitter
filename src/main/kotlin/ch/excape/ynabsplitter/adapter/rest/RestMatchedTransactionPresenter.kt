package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.MatchedTransactionDocument
import ch.excape.ynabsplitter.adapter.rest.document.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.domain.MatchedTransaction

class RestMatchedTransactionPresenter : MatchedTransactionPresenter {

    var presentation: MatchedTransactionDocument? = null

    override fun present(matchedTransaction: MatchedTransaction?) {
        presentation = matchedTransaction?.toDocument()
    }
}

