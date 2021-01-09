package ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo

import ch.excape.ynabsplitter.adapter.persistence.user.mongo.ActorEntity
import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.domain.*
import ch.excape.ynabsplitter.rest.toThreetenLocalDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "auditlogs")
data class AuditLogEntity(
        val id: String,
        val date: LocalDateTime,
        val userId: String,
        val executingActor: String,
        val oldTransactions: List<TransactionEntity>,
        val newTransactions: List<TransactionEntity>
) {
    fun toDomain() = AuditLog(
            id,
            date,
            userId,
            executingActor,
            oldTransactions.map {it.toDomain()},
            newTransactions.map {it.toDomain()}
    )
}

private fun Map<String, TransactionEntity>.toDomain(): Map<ActorName, Transaction> {
    return entries.associate { ActorName(it.key) to it.value.toDomain() }
}

data class TransactionEntity(
        val id: String,
        val actor: ActorEntity,
        val date: LocalDate,
        val amount: Long,
        val categoryId: String?,
        val categoryName: String?,
        val memo: String?,
        val isApproved: Boolean,
        val payee: String?
) {
    fun toDomain() = Transaction(
            id,
            date.toThreetenLocalDate(),
            amount,
            if (categoryId != null) Category(categoryId, categoryName, null, null) else null,
            memo,
            isApproved,
            payee,
            actor.toDomain()
    )
}

fun AuditLog.toEntity() = AuditLogEntity(
        id,
        date,
        userId,
        executingActor,
        oldTransactions.map { it.toEntity() },
        newTransactions.map { it.toEntity() }
)

private fun SplitterActor.toEntity(): ActorEntity =
        ActorEntity(actorName.name, budgetId, accountId)

fun Transaction.toEntity() = TransactionEntity(
        id,
        actor.toEntity(),
        date.toJavaLocalDate(),
        amount,
        category?.id,
        category?.name,
        memo,
        isApproved,
        payee
)