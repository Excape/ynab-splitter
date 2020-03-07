package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionPresenter
import ch.excape.ynabsplitter.domain.Transaction

class RestTransactionPresenter : TransactionPresenter {

    var presentation: Transaction? = null

    override fun present(transaction: Transaction?) {
        presentation = transaction
    }
}