package ch.excape.ynabsplitter.application.use_cases.approve_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.MatchedTransactionIds
import ch.excape.ynabsplitter.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalDate

class ApproveTransactionTest {

    private val actorAlice = SplitterActor(ActorName("Alice"), "test", "test")
    private val actorBob = SplitterActor(ActorName("Bob"), "test", "test")
    private val fakeUser = User("someID", UserSettings(listOf(actorAlice, actorBob)))

    private val testTransactions = listOf(
            Transaction("1", LocalDate.now(), 100, null, null, false, null, actorBob),
            Transaction("2", LocalDate.now(), 100, null, null, false, null, actorAlice)
    )

    @Test
    fun approveTransaction_simpleSplit() {

        val savedTransactions = mutableListOf<Transaction>()
        val transactionRepository = object : SaveTransactionRepository, ReadTransactionsRepository {
            override fun saveTransaction(transaction: Transaction) {
                savedTransactions.add(transaction)
            }

            override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> {
                TODO("not implemented")
            }

            override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
                return testTransactions.find { it.id == id}
            }

            override fun triggerTransactionImport(actor: SplitterActor) {
                TODO("not implemented")
            }
        }

        val auditLogEntries = mutableListOf<AuditLog>()
        val auditLogRepository = object : AuditLogRepository {
            override fun saveAuditLog(auditLog: AuditLog) {
                auditLogEntries.add(auditLog)
            }

            override fun getAllAuditLogs(): List<AuditLog> {
                TODO("not implemented")
            }
        }
        val categoriesRepository = object: ReadCategoriesRepository {
            override fun getCategories(actor: SplitterActor): List<Category> {
                return emptyList()
            }

            override fun findCategory(actor: SplitterActor, categoryId: String): Category? {
                return null
            }

        }
        val userRepository = object : UserRepository {
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
        val approveTransaction = ApproveTransaction(transactionRepository, transactionRepository,
                categoriesRepository, auditLogRepository, userRepository)

        val t1 = Transaction("1", LocalDate.now(), 100, null, null, false, null, actorBob)
        val t2 = Transaction("2", LocalDate.now(), 100, null, null, false, null, actorAlice)
        val input = ApproveTransactionInput(
                fakeUser.userId,
                MatchedTransactionIds(mapOf(
                        actorBob.actorName.name to t1.id,
                        actorAlice.actorName.name to t2.id
                       )),
                actorBob.actorName.name,
                TransactionSplit(actorAlice.actorName.name to 0.5, actorBob.actorName.name to 0.5),
                CategoryPerActor(actorAlice.actorName.name to Category("k1"), actorBob.actorName.name to Category("k2"))
        )

        val presenter = object : ApproveTransactionPresenter {
            override fun present(result: ApproveTransactionResult) {
                assertThat(result.success).isTrue
            }
        }

        approveTransaction.executeWith(input, presenter)

        assertThat(savedTransactions)
                .allSatisfy { transaction ->
                    assertThat(transaction).extracting { it.amount}.isEqualTo(50L)
                    assertThat(transaction).extracting { it.isApproved}.isEqualTo(true)
                }
    }
}