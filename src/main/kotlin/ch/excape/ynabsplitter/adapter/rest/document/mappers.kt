package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.domain.*

fun Category.toDocument(): CategoryDocument = CategoryDocument(id, name ?: "", group, balance)

fun MatchedTransaction.toDocument(): MatchedTransactionDocument {
    return MatchedTransactionDocument(this.transactions.map { it.toDocument() })
}

fun Transaction.toDocument(): TransactionDocument {
    return TransactionDocument(id, actor.toDocument(), date.toJavaLocalDate(), amount, category?.toDocument(), memo, payee)
}

fun AuditLog.toDocument(): AuditLogDocument = AuditLogDocument(
        id,
        date,
        executingActor,
        oldTransactions.associate { it.actor.actorName.name to it.toDocument() },
        newTransactions.associate { it.actor.actorName.name to it.toDocument() }
)

fun User.toDocument(): UserDocument = UserDocument(userId, settings.toDocument())

fun UserSettings.toDocument(): UserSettingsDocument = UserSettingsDocument(actors.map { it.toDocument() })

fun SplitterActor.toDocument(): SplitterActorDocument = SplitterActorDocument(actorName.name, budgetId, accountId)

fun Budget.toDocument(): BudgetDocument =
        BudgetDocument(name, budgetId, accounts.map { it.toDocument() })

fun BudgetAccount.toDocument(): AccountDocument =
        AccountDocument(name, accountId)
