package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction

import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.threeten.bp.LocalDate

internal class GetMatchedTransactionTest {

    @Test
    internal fun getMatchedTransaction() {
        val input = GetMatchedTransactionInput("t1_t3")

        val getMatchedTransaction = GetMatchedTransaction(transactionRepository)

        val presenter = object : MatchedTransactionPresenter {
            override fun present(matchedTransaction: MatchedTransaction?) {
                assertThat(matchedTransaction)
                        .isNotNull
                assertThat(matchedTransaction!!.transactions)
                        .isNotEmpty
                        .containsExactly(testTransactions[0], testTransactions[2])
            }
        }

        getMatchedTransaction.executeWith(input, presenter)
    }

    @Test
    internal fun getInvalidTransaction() {
        val input = GetMatchedTransactionInput("t1_t100")

        val getMatchedTransaction = GetMatchedTransaction(transactionRepository)

        val presenter = object : MatchedTransactionPresenter {
            override fun present(matchedTransaction: MatchedTransaction?) {
            }
        }

        assertThrows<IllegalArgumentException> { getMatchedTransaction.executeWith(input, presenter) }
    }


    val testTransactions = listOf(
            testTransaction("t1", 1400, "payee1", Actor.ROBIN),
            testTransaction("t2", 200, "payee2", Actor.ROBIN),
            testTransaction("t3", 1400, "payee1", Actor.SOPHIE),
            testTransaction("t4", 200, "payee2", Actor.SOPHIE)
    )

    private val transactionRepository = object : ReadTransactionsRepository {
        override fun getAllTransactionsFromLastWeek(actor: Actor): List<Transaction> {
            throw NotImplementedError()
        }

        override fun getAllTransactionsBetween(actor: Actor, startDate: LocalDate, endDate: LocalDate): List<Transaction> {
            throw NotImplementedError()
        }

        override fun getUnapprovedTransactionsFromLastMonth(actor: Actor): List<Transaction> {
            throw NotImplementedError()
        }

        override fun getTransaction(actor: Actor, id: String): Transaction? {
            return testTransactions.find { it.id == id && it.actor == actor }
        }

        override fun triggerTransactionImport(actor: Actor) {
            throw NotImplementedError()
        }
    }

    private fun testTransaction(id: String, amount: Long, payee: String, actor: Actor) = Transaction(
            id, LocalDate.now(), amount, null, null, false, payee, actor)

}
