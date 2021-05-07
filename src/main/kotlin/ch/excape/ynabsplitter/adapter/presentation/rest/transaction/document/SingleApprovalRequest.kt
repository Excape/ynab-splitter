package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

data class SingleApprovalRequest(
        val actor: String,
        val categoryId: String,
        val executingActor: String
)
