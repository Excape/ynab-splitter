package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import org.threeten.bp.LocalDate

interface ReadTransactionsRepository {
    fun getAllTransactionsFromLastWeek(actor: SplitterActor): List<Transaction>
    fun getAllTransactionsBetween(actor: SplitterActor, startDate: LocalDate, endDate: LocalDate): List<Transaction>
    fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction>
    fun getTransaction(actor: SplitterActor, id: String): Transaction?
    fun triggerTransactionImport(actor: SplitterActor)
}