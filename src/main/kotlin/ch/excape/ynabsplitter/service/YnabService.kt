package ch.excape.ynabsplitter.service

import ch.excape.ynabclient.api.AccountsApi
import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.model.TransactionsResponse
import ch.excape.ynabsplitter.domain.Actor
import org.springframework.stereotype.Service
import org.threeten.bp.LocalDate

@Service
class YnabService(private val transactionsApi: TransactionsApi) {
    fun getUnapprovedTransactions(actor: Actor) {
        val transactions : TransactionsResponse = transactionsApi.getTransactionsByAccount(
                actor.budgetId, actor.accountId, lastWeek(),
                "unapproved", null)
        print(transactions.data.transactions)
    }

    private fun lastWeek() : LocalDate = LocalDate.now().minusWeeks(1)
}