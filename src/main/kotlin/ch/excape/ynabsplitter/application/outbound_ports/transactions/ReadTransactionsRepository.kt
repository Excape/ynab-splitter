package ch.excape.ynabsplitter.application.outbound_ports.transactions

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Transaction
import org.threeten.bp.LocalDate

interface ReadTransactionsRepository {
    fun getAllTransactionsFromLastWeek(actor: Actor): List<Transaction>
    fun getAllTransactionsBetween(actor: Actor, startDate: LocalDate, endDate: LocalDate): List<Transaction>
    fun getUnapprovedTransactionsFromLastWeek(actor: Actor): List<Transaction>
    fun getTransaction(actor: Actor, id: String): Transaction?
}