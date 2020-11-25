package ch.excape.ynabsplitter.domain

import java.time.LocalDateTime

data class AuditLog(
        val date: LocalDateTime,
        val oldTransaction: Transaction,
        val newTransaction: Transaction,
        val executingActor: String
)
