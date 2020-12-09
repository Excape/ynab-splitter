package ch.excape.ynabsplitter.adapter.rest.document

import java.time.LocalDateTime

data class AuditLogDocument(
        val date: LocalDateTime,
        val oldTransaction: TransactionDocument,
        val newTransaction: TransactionDocument,
        val executingActor: String
)