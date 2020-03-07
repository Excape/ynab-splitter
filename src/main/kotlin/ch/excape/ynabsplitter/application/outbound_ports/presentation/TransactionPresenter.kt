package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.Transaction

interface TransactionPresenter {
    fun present(transaction: Transaction?)
}
