package ch.excape.ynabsplitter.adapter.rest.document

data class AddActorRequest(
        val actorName: String,
        val budgetId: String,
        val accountId: String
)
