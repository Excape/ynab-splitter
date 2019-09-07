package ch.excape.ynabsplitter.service

import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.model.TransactionDetail
import ch.excape.ynabclient.model.TransactionsResponse
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Transaction
import org.springframework.stereotype.Service
import org.threeten.bp.LocalDate

@Service
class YnabService(private val transactionsApi: TransactionsApi) {
    fun getAllTransactions(actor: Actor): List<Transaction> = getTransactions(actor, false)

    fun getUnapprovedTransactions(actor: Actor): List<Transaction> = getTransactions(actor, true)

    private fun getTransactions(actor: Actor, unapprovedOnly: Boolean): List<Transaction> {
        val response: TransactionsResponse = transactionsApi.getTransactionsByAccount(
                actor.budgetId, actor.accountId, lastWeek(),
                if (unapprovedOnly) "unapproved" else null, null)

        return response.data.transactions.map(TransactionDetail::toTransaction)
    }

    private fun lastWeek() : LocalDate = LocalDate.now().minusWeeks(1)
}