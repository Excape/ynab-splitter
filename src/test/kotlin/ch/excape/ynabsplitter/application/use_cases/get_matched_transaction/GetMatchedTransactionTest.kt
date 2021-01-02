package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.threeten.bp.LocalDate

internal class GetMatchedTransactionTest {

    private val actorAlice = SplitterActor(ActorName("Alice"), "test", "test")
    private val actorBob = SplitterActor(ActorName("Bob"), "test", "test")
    private val fakeUser = User("someID", UserSettings(listOf(actorAlice, actorBob)))

    val testTransactions = listOf(
            testTransaction("t1", 1400, "payee1", actorAlice),
            testTransaction("t2", 200, "payee2", actorBob),
            testTransaction("t3", 1400, "payee1", actorAlice),
            testTransaction("t4", 200, "payee2", actorBob)
    )

    private val transactionRepository = object : ReadTransactionsRepository {
        override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> {
            return testTransactions.filter { it.actor.actorName == actor.actorName}
        }

        override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
            return testTransactions.find { it.id == id}
        }

        override fun triggerTransactionImport(actor: SplitterActor) {
            return
        }
    }

    private val fakeUserRepository: UserRepository = object : UserRepository {
        override fun getUser(userId: String): User {
            return fakeUser
        }

        override fun createUser(user: User) {
            TODO("not implemented")
        }

        override fun updateUser(user: User) {
            TODO("not implemented")
        }

    }


    @Test
    internal fun getMatchedTransaction() {
        val input = GetMatchedTransactionInput(fakeUser.userId, "t1_t3")

        val getMatchedTransaction = GetMatchedTransaction(transactionRepository, fakeUserRepository)

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
        val input = GetMatchedTransactionInput(fakeUser.userId, "t1_t100")

        val getMatchedTransaction = GetMatchedTransaction(transactionRepository, fakeUserRepository)

        val presenter = object : MatchedTransactionPresenter {
            override fun present(matchedTransaction: MatchedTransaction?) {
            }
        }

        assertThrows<IllegalArgumentException> { getMatchedTransaction.executeWith(input, presenter) }
    }

    private fun testTransaction(id: String, amount: Long, payee: String, actor: SplitterActor) = Transaction(
            id, LocalDate.now(), amount, null, null, false, payee, actor)

}
