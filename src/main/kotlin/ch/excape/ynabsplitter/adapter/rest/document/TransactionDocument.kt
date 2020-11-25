package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.domain.Actor
import java.time.LocalDate

data class TransactionDocument(
    val id: String,
    val actor: SplitterActorDocument,
    val date: LocalDate,
    val amount: Long,
    val category: CategoryDocument?,
    val memo: String?,
    val payee: String?
)
