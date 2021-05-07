package ch.excape.ynabsplitter.domain

import org.threeten.bp.LocalDate

data class Transaction(
        val id: String,
        val date: LocalDate,
        val amount: Long,
        val category: Category?,
        val memo: String?,
        val isApproved: Boolean,
        val payee: String?,
        val actor: SplitterActor
) {
    fun matches(other: Transaction): Boolean {
        return actor != other.actor && date == other.date && amount == other.amount
    }
}