package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.model.SaveTransactionWrapper
import ch.excape.ynabclient.model.TransactionResponse
import ch.excape.ynabclient.model.TransactionsResponse
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.threeten.bp.LocalDate

class YnabTransactionRepository(@Qualifier("ynabTransactionsApi") private val transactionsApi: TransactionsApi)
    : ReadTransactionsRepository, SaveTransactionRepository {
    override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
        val transactionResponse: TransactionResponse?
        try {
            transactionResponse = transactionsApi.getTransactionById(actor.budgetId, id)
        } catch (ex: HttpClientErrorException) {
            if (ex.statusCode == HttpStatus.NOT_FOUND) {
                return null
            }
            throw ex
        }
        return transactionResponse.data.transaction.toTransaction(actor)
    }

    override fun triggerTransactionImport(actor: SplitterActor) {
        transactionsApi.importTransactions(actor.budgetId)
    }

    override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> = getTransactions(actor, true, lastMonth())

    private fun getTransactions(actor: SplitterActor, unapprovedOnly: Boolean, startDate: LocalDate): List<Transaction> {
        val response: TransactionsResponse = transactionsApi.getTransactionsByAccount(
                actor.budgetId, actor.accountId, startDate,
                if (unapprovedOnly) "unapproved" else null, null)

        return response.data.transactions.map {it.toTransaction(actor)}
    }

    private fun lastMonth() : LocalDate = LocalDate.now().minusMonths(1)


    override fun saveTransaction(transaction: Transaction) {
        transactionsApi.updateTransaction(wrapTransaction(transaction), transaction.actor.budgetId, transaction.id)
    }

    private fun wrapTransaction(transaction: Transaction): SaveTransactionWrapper {
        return SaveTransactionWrapper()
                .transaction(transaction.toSaveTransaction())
    }
}