package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

data class CategoryDocument(
        val id: String,
        val name: String,
        val group: String?,
        val balance: Long?
)
