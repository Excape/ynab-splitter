package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

data class SplitTransactionRequest(
        val executingActor: String,
        val categories: List<CategoryRequest>,
        val split: List<SplitRequest>
)

data class CategoryRequest(
        val actor: String,
        val categoryId: String
)

data class SplitRequest(
        val actor: String,
        val split: Double
)
