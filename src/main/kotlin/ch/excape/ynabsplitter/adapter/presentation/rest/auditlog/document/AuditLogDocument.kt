package ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document

import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.TransactionDocument
import java.time.LocalDateTime

data class AuditLogDocument(
        val id: String,
        val date: LocalDateTime,
        val payee: String?,
        val executingActor: String,
        val transactions: List<TransactionsByActorDocument>,
)

data class TransactionsByActorDocument(
    val actor: String,
    val oldTransaction: TransactionDocument,
    val newTransaction: TransactionDocument
)