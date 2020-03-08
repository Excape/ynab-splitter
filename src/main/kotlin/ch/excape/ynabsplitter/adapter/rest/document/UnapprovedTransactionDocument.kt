package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.domain.Actor
import java.time.LocalDate

data class UnapprovedTransactionDocument(
        val id: String,
        val date: LocalDate,
        val amount: Long,
        val category: Map<Actor, String>,
        val memo: String?,
        val payee: String?
)