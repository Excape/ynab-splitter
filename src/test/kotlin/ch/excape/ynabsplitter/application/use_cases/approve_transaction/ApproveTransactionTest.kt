package ch.excape.ynabsplitter.application.use_cases.approve_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.ApproveTransactionInput
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate

class ApproveTransactionTest {

    @Test
    fun approveTransaction_simpleSplit() {

        val savedTransactions = mutableListOf<Transaction>()
        val saveTransactionRepository = object : SaveTransactionRepository {
            override fun saveTransaction(transaction: Transaction) {
                savedTransactions.add(transaction)
            }
        }

        val auditLogEntries = mutableListOf<AuditLog>()
        val auditLogRepository = object : AuditLogRepository {
            override fun saveAuditLog(auditLog: AuditLog) {
                auditLogEntries.add(auditLog)
            }
        }
        val approveTransaction = ApproveTransaction(saveTransactionRepository, auditLogRepository)

        val input = ApproveTransactionInput(
                MatchedTransaction(mutableListOf(
                            Transaction("1", LocalDate.now(), 100, null, null, false, null, Actor.SOPHIE),
                            Transaction("2", LocalDate.now(), 100, null, null, false, null, Actor.ROBIN)
                        )),
                Actor.ROBIN,
                TransactionSplit(Actor.ROBIN to 0.5, Actor.SOPHIE to 0.5),
                CategoryPerActor(Actor.ROBIN to Category("k1"), Actor.SOPHIE to Category("k2"))
        )

        val presenter = object : ApproveTransactionPresenter {
            override fun present(result: ApproveTransactionResult) {
                assertThat(result.success).isTrue()
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