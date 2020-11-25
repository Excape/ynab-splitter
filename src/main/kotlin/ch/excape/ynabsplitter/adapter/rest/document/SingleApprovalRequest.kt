package ch.excape.ynabsplitter.adapter.rest.document

data class SingleApprovalRequest(
        val actor: String,
        val categoryId: String,
        val executingActor: String
)
