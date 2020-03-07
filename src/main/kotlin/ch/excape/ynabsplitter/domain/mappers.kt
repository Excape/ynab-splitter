package ch.excape.ynabsplitter.domain

import ch.excape.ynabclient.model.TransactionDetail
import ch.excape.ynabsplitter.adapter.csv.ChaseTransaction
import org.threeten.bp.LocalDate
import java.time.ZoneId
import java.util.*

fun TransactionDetail.toTransaction(actor: Actor) = Transaction(
        id, date, amount, categoryName, memo, isApproved, payeeName, actor)

fun ChaseTransaction.toTransaction() = Transaction(
        randomId(),
        transactionDate!!.toLocalDate(),
        amount!!.times(1000).toLong(),
        null, null, false, description, null)

private fun randomId() = Random().nextInt().toString()

fun Date.toLocalDate() : LocalDate {
    val localDate = this.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    // convert from java LocalDate to this weird threeten.bp.LocalDate
    return LocalDate.of(localDate.year, localDate.monthValue, localDate.dayOfMonth)
}