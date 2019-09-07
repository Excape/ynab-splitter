package ch.excape.ynabsplitter.domain

import org.threeten.bp.LocalDate

data class Transaction(
        val id: String,
        val date: LocalDate,
        val amount: Long,
        val memo: String?,
        val isApproved: Boolean,
        val payee: String?
)