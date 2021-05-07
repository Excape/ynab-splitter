package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SplitterActorDocument
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
