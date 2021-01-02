package ch.excape.ynabsplitter.adapter.rest.document

import java.time.LocalDateTime

data class AuditLogDocument(
        val id: String,
        val date: LocalDateTime,
        val executingActor: String,
        val oldTransactions: Map<String, TransactionDocument>,
        val newTransactions: Map<String, TransactionDocument>,
)