package ch.excape.ynabsplitter.application.use_cases.get_transaction

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.transactions.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.get_transaction.ports.IGetTransaction
import ch.excape.ynabsplitter.domain.Actor

class GetTransaction(private val transactionRepository: ReadTransactionsRepository): IGetTransaction {

    override fun executeWith(actor: Actor, id: String, presenter: TransactionPresenter) {
        val transaction = transactionRepository.getTransaction(actor, id)
        presenter.present(transaction)
    }

}