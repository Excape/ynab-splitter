package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction

interface ReadTransactionsRepository {
    fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction>
    fun getTransaction(actor: SplitterActor, id: String): Transaction?
    fun triggerTransactionImport(actor: SplitterActor)
}