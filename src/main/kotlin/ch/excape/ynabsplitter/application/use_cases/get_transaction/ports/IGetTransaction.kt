package ch.excape.ynabsplitter.application.use_cases.get_transaction.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionPresenter
import ch.excape.ynabsplitter.domain.Actor

interface IGetTransaction {
    fun executeWith(actor: Actor, id: String, presenter: TransactionPresenter)
}