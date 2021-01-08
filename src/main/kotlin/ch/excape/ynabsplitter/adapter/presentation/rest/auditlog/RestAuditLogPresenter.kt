package ch.excape.ynabsplitter.adapter.presentation.rest.auditlog

import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.AuditLogDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.TransactionsByActorDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AuditLogPresenter
import ch.excape.ynabsplitter.domain.AuditLog
import ch.excape.ynabsplitter.domain.Transaction

class RestAuditLogPresenter : AuditLogPresenter {
    var presentation: List<AuditLogDocument>? = null

    override fun present(auditLog: List<AuditLog>) {
        presentation = auditLog.map { it.toDocument() }
    }

}

fun AuditLog.toDocument(): AuditLogDocument = AuditLogDocument(
        id,
        date,
        newTransactions.first().payee,
        executingActor,
        mapTransactionsByActor(oldTransactions, newTransactions)
)

private fun mapTransactionsByActor(oldTransactions: List<Transaction>, newTransactions: List<Transaction>) =
        oldTransactions.map { oldTransaction ->
            TransactionsByActorDocument(
                    oldTransaction.actor.actorName.name,
                    oldTransaction.toDocument(),
                    findMatchingNewTransaction(oldTransaction, newTransactions).toDocument()
            )
        }

fun findMatchingNewTransaction(oldTransaction: Transaction, newTransactions: List<Transaction>): Transaction =
        newTransactions.find {it.actor.actorName == oldTransaction.actor.actorName}
                ?: throw IllegalArgumentException("No matching audit log transaction for ${oldTransaction.actor.actorName}")
