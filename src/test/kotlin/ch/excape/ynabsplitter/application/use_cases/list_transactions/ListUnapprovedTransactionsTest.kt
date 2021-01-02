package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDate

internal class ListUnapprovedTransactionsTest {

    private val actorAlice = SplitterActor(ActorName("Alice"), "test", "test")
    private val actorBob = SplitterActor(ActorName("Bob"), "test", "test")

    private val fakeUser = User("someID", UserSettings(listOf(actorAlice, actorBob)))

    @Test
    fun matchTransactions_differentTransactions_returnNone() {
        val testTransactions = listOf(
                testTransaction("t1", 1200, "payee1", actorAlice),
                testTransaction("t2", 1400, "payee2", actorBob)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(0)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository, mockUserRepository())

        testee.executeWith(ListUnapprovedTransactionsInput(fakeUser.userId), presenter)
    }

    @Test
    fun matchTransactions_twoMatchingTransactions() {
        val testTransactions = listOf(
                testTransaction("t1", 1400, "payee1", actorAlice),
                testTransaction("t2", 200, "payee2", actorAlice),
                testTransaction("t3", 1400, "payee1", actorBob),
                testTransaction("t4", 200, "payee2", actorBob)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(2)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository, mockUserRepository())

        testee.executeWith(ListUnapprovedTransactionsInput(fakeUser.userId), presenter)
    }

    @Test
    fun matchTransactions_twoSimilarTransactions() {
        val testTransactions = listOf(
                testTransaction("t1", 1400, "payee1", actorAlice),
                testTransaction("t2", 1400, "payee1", actorBob),
                testTransaction("t3", 1400, "payee1", actorAlice),
                testTransaction("t4", 1400, "payee1", actorBob)
        )

        val presenter = object : TransactionListPresenter {
            override fun present(transactions: List<MatchedTransaction>) {
                assertThat(transactions)
                        .hasSize(2)
            }
        }

        val transactionRepository = mockReadRepository(testTransactions)

        val testee = ListUnapprovedTransactions(transactionRepository, mockUserRepository())

        testee.executeWith(ListUnapprovedTransactionsInput(fakeUser.userId), presenter)

    }

    private fun mockUserRepository(): UserRepository {
        return object : UserRepository {
            override fun getUser(userId: String): User = fakeUser

            override fun createUser(user: User) {
                TODO("not implemented")
            }

            override fun updateUser(user: User) {
                TODO("not implemented")
            }

        }
    }


    private fun mockReadRepository(testTransactions: List<Transaction>): ReadTransactionsRepository {
        return object : ReadTransactionsRepository {
            override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> {
                return testTransactions.filter { it.actor.actorName == actor.actorName}
            }

            override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
                TODO("not implemented")
            }

            override fun triggerTransactionImport(actor: SplitterActor) {
                return
            }

        }
    }

    private fun testTransaction(id: String, amount: Long, payee: String, actor: SplitterActor) = Transaction(
            id, LocalDate.now(), amount, null, null, false, payee, actor)
}
