package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.domain.Actor
import java.time.LocalDateTime

data class AuditLogDocument(
        val date: LocalDateTime,
        val oldTransaction: TransactionDocument,
        val newTransaction: TransactionDocument,
        val executingActor: String
)