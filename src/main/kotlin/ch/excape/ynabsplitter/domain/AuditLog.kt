package ch.excape.ynabsplitter.domain

import java.time.LocalDateTime

data class AuditLog(
        val id: String,
        val date: LocalDateTime,
        val userId: String,
        val executingActor: String,
        val oldTransactions: List<Transaction>,
        val newTransactions: List<Transaction>
)
