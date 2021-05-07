package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.model.SaveTransactionWrapper
import ch.excape.ynabclient.model.TransactionResponse
import ch.excape.ynabclient.model.TransactionsResponse
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.threeten.bp.LocalDate

class YnabTransactionRepository(
        private val transactionsApi: TransactionsApi,
        private val dryRun: Boolean
) : ReadTransactionsRepository, SaveTransactionRepository {

    companion object {
        val log: Logger = LogManager.getLogger()
    }

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

    override fun triggerTransactionImport(actor: SplitterActor): Int {
        val result = transactionsApi.importTransactions(actor.budgetId)
        return result.data.transactionIds.count()
    }

    override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> = getTransactions(actor, true, lastMonth())

    private fun getTransactions(actor: SplitterActor, unapprovedOnly: Boolean, startDate: LocalDate): List<Transaction> {
        val response: TransactionsResponse = transactionsApi.getTransactionsByAccount(
                actor.budgetId, actor.accountId, startDate,
                if (unapprovedOnly) "unapproved" else null, null)

        return response.data.transactions.map { it.toTransaction(actor) }
    }

    private fun lastMonth(): LocalDate = LocalDate.now().minusMonths(1)


    override fun saveTransaction(transaction: Transaction) {
        if (!dryRun) {
            transactionsApi.updateTransaction(wrapTransaction(transaction), transaction.actor.budgetId, transaction.id)
        } else {
            log.info("Dry run enabled, not saving transaction to ynab")
        }
    }

    private fun wrapTransaction(transaction: Transaction): SaveTransactionWrapper {
        return SaveTransactionWrapper()
                .transaction(transaction.toSaveTransaction())
    }
}