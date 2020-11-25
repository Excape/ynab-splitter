package ch.excape.ynabsplitter.adapter.persistence.auditlog

import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.domain.*
import ch.excape.ynabsplitter.rest.toThreetenLocalDate
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDate
import java.time.LocalDateTime

@RedisHash("AuditLog")
data class AuditLogEntity(
        val id: String,
        val date: LocalDateTime,
        val oldTransaction: TransactionEntity,
        val newTransaction: TransactionEntity,
        val executingActor: String
) {
    fun toDomain() = AuditLog(
            date,
            oldTransaction.toDomain(),
            newTransaction.toDomain(),
            executingActor
    )
}

data class TransactionEntity(
        val id: String,
        val actor: String,
        val date: LocalDate,
        val amount: Long,
        val categoryId: String?,
        val categoryName: String?,
        val memo: String?,
        val payee: String?
) {
    fun toDomain() = Transaction(
            id,
            date.toThreetenLocalDate(),
            amount,
            if (categoryId != null) Category(categoryId, categoryName, null, null) else null,
            memo,
            true,
            payee,
            // TODO we might want everything in the audit log
            SplitterActor(actor, "NO-ID", "NO-ID")
    )
}

fun AuditLog.toEntity() = AuditLogEntity(
        newTransaction.id,
        date,
        oldTransaction.toEntity(),
        newTransaction.toEntity(),
        executingActor
)
fun Transaction.toEntity() = TransactionEntity(
        id,
        actor.name,
        date.toJavaLocalDate(),
        amount,
        category?.id,
        category?.name,
        memo,
        payee
)