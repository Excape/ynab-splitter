package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDate

internal class ListUnapprovedTransactionsTest {


    @Test
    fun matchTransactions_differentTransactions() {
        val testTransactions = listOf(
                testTransaction("t1", 1200, "payee1", Actor.ROBIN),
                testTransaction("t2", 1400, "payee2", Actor.ROBIN)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(2)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository)

        testee.executeWith(ListUnapprovedTransactionsInput(listOf(Actor.SOPHIE, Actor.ROBIN)), presenter)
    }

    @Test
    fun matchTransactions_twoMatchingTransactions() {
        val testTransactions = listOf(
                testTransaction("t1", 1400, "payee1", Actor.ROBIN),
                testTransaction("t2", 200, "payee2", Actor.ROBIN),
                testTransaction("t3", 1400, "payee1", Actor.SOPHIE),
                testTransaction("t4", 200, "payee2", Actor.SOPHIE)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(2)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository)

        testee.executeWith(ListUnapprovedTransactionsInput(listOf(Actor.SOPHIE, Actor.ROBIN)), presenter)
    }

    @Test
    fun matchTransactions_twoSimilarTransactions() {
        val testTransactions = listOf(
                testTransaction("t1", 1400, "payee1", Actor.ROBIN),
                testTransaction("t2", 1400, "payee1", Actor.SOPHIE),
                testTransaction("t3", 1400, "payee1", Actor.ROBIN),
                testTransaction("t4", 1400, "payee1", Actor.SOPHIE)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(2)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository)

        testee.executeWith(ListUnapprovedTransactionsInput(listOf(Actor.SOPHIE, Actor.ROBIN)), presenter)

    }


    private fun mockReadRepository(testTransactions: List<Transaction>): ReadTransactionsRepository {
        return object : ReadTransactionsRepository {
            override fun getTransaction(actor: Actor, id: String): Transaction? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getAllTransactionsBetween(actor: Actor, startDate: LocalDate, endDate: LocalDate): List<Transaction> {
                return emptyList()
            }

            override fun getUnapprovedTransactionsFromLastWeek(actor: Actor): List<Transaction> {
                return emptyList()
            }

            override fun getAllTransactionsFromLastWeek(actor: Actor): List<Transaction> {
                return testTransactions.filter { it.actor == actor }
            }
        }
    }

    private fun testTransaction(id: String, amount: Long, payee: String, actor: Actor) = Transaction(
            id, LocalDate.now(), amount, null, null, false, payee, actor)
}
