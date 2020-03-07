package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.model.TransactionsResponse
import ch.excape.ynabsplitter.application.outbound_ports.transactions.ReadTransactionsRepository
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.toTransaction
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.threeten.bp.LocalDate

@Service
class YnabService(@Qualifier("ynabTransactionsApi") private val transactionsApi: TransactionsApi) : ReadTransactionsRepository {
    override fun getTransaction(actor: Actor, id: String): Transaction? {
        val transactionResponse = transactionsApi.getTransactionById(actor.budgetId, id)
        return transactionResponse.data.transaction.toTransaction(actor)
    }

    override fun getAllTransactionsFromLastWeek(actor: Actor): List<Transaction> = getTransactions(actor, false, lastWeek())

    override fun getAllTransactionsBetween(actor: Actor, startDate: LocalDate, endDate: LocalDate): List<Transaction> {
        val transactionsSinceStart = getTransactions(actor, false, startDate)
        return transactionsSinceStart.filter { it.date <= endDate }
    }

    override fun getUnapprovedTransactionsFromLastWeek(actor: Actor): List<Transaction> = getTransactions(actor, true, lastWeek())

    private fun getTransactions(actor: Actor, unapprovedOnly: Boolean, startDate: LocalDate): List<Transaction> {
        val response: TransactionsResponse = transactionsApi.getTransactionsByAccount(
                actor.budgetId, actor.accountId, startDate,
                if (unapprovedOnly) "unapproved" else null, null)

        return response.data.transactions.map {it.toTransaction(actor)}
    }

    private fun lastWeek() : LocalDate = LocalDate.now().minusWeeks(1)
}