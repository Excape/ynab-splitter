package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.adapter.rest.document.*
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.CategoryPerActor
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.TransactionSplit
import java.time.LocalDate

fun MatchedTransactionDocument.toDomain(): MatchedTransaction =
        MatchedTransaction(transactions.map { it.toDomain() }.toMutableList())

fun TransactionDocument.toDomain(): Transaction =
        Transaction(
                id,
                date.toThreetenLocalDate(),
                amount,
                category?.toDomain(),
                memo,
                false,
                payee,
                actor
        )

fun CategoryDocument.toDomain(): Category = Category(id, name, group, balance)

fun LocalDate.toThreetenLocalDate(): org.threeten.bp.LocalDate {
    // convert from java LocalDate to this weird threeten.bp.LocalDate
    return org.threeten.bp.LocalDate.of(year, monthValue, dayOfMonth)
}

fun List<SplitRequest>.toDomain(): TransactionSplit {
    return TransactionSplit(this.associate { it.actor to it.split })
}

fun List<CategoryRequest>.toDomain(): CategoryPerActor {
    return CategoryPerActor(this.associate { it.actor to Category(it.categoryId) })
}