package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

import java.time.LocalDate

data class UnapprovedTransactionDocument(
        val id: String,
        val date: LocalDate,
        val actors: List<String>,
        val amount: Long,
        val categoryMap: List<ActorCategoryDocument>,
        val memo: String?,
        val payee: String?
)