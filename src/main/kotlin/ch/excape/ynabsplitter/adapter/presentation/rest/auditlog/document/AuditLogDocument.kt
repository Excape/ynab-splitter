package ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document

import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.TransactionDocument
import java.time.LocalDateTime

data class AuditLogDocument(
        val id: String,
        val date: LocalDateTime,
        val executingActor: String,
        val oldTransactions: Map<String, TransactionDocument>,
        val newTransactions: Map<String, TransactionDocument>,
)