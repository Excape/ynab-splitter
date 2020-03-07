package ch.excape.ynabsplitter.adapter.rest.document

import java.time.LocalDate

data class UnapprovedTransactionDocument(
        val id: String,
        val date: LocalDate,
        val amount: Long,
        val category: String?,
        val memo: String?,
        val payee: String?
)