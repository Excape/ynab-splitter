package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.model.SaveTransaction
import ch.excape.ynabclient.model.TransactionDetail
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.Transaction
import org.threeten.bp.LocalDate
import java.util.*

fun TransactionDetail.toTransaction(actor: Actor) = Transaction(
        id, date, amount, mapCategory(this), memo, isApproved, payeeName, actor)

fun mapCategory(transactionDetail: TransactionDetail): Category? {
    return if (transactionDetail.categoryId != null)
        Category(
                transactionDetail.categoryId.toString(),
                transactionDetail.categoryName,
                null, null)
    else null
}

fun LocalDate.toJavaLocalDate(): java.time.LocalDate =
        java.time.LocalDate.of(this.year, this.monthValue, this.dayOfMonth)

fun ch.excape.ynabclient.model.Category.toCategory(categoryGroup: String): Category {
    return Category(id.toString(), name, categoryGroup, balance)
}

fun Transaction.toSaveTransaction(accountId: String): SaveTransaction =
        SaveTransaction()
                .accountId(UUID.fromString(accountId))
                .date(date)
                .amount(amount)
                .categoryId(UUID.fromString(category?.id))
                .payeeName(payee)
                .approved(isApproved)
                .cleared(SaveTransaction.ClearedEnum.CLEARED)