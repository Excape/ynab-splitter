package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.domain.Actor

data class SplitTransactionRequest(
        val categories: List<CategoryRequest>,
        val split: List<SplitRequest>
)

data class CategoryRequest(
        val actor: Actor,
        val categoryId: String
)

data class SplitRequest(
        val actor: Actor,
        val split: Double
)
