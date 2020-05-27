package ch.excape.ynabsplitter.adapter.persistence

import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.AuditLog
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.rest.toThreetenLocalDate
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDate

@RedisHash("AuditLog")
data class AuditLogEntity(
        val id: String,
        val oldTransaction: TransactionEntity,
        val newTransaction: TransactionEntity,
        val executingActor: Actor
) {
    fun toDomain() = AuditLog(
            oldTransaction.toDomain(),
            newTransaction.toDomain(),
            executingActor
    )
}

data class TransactionEntity(
        val id: String,
        val actor: Actor,
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
            actor
    )
}

fun AuditLog.toEntity() = AuditLogEntity(
        newTransaction.id,
        oldTransaction.toEntity(),
        newTransaction.toEntity(),
        executingActor
)
fun Transaction.toEntity() = TransactionEntity(
        id,
        actor,
        date.toJavaLocalDate(),
        amount,
        category?.id,
        category?.name,
        memo,
        payee
)