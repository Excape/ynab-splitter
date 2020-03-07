package ch.excape.ynabsplitter.domain

data class AuditLog(
        val oldTransaction: Transaction,
        val newTransaction: Transaction,
        val executingActor: Actor
)
