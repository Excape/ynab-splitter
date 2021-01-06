package ch.excape.ynabsplitter.adapter.presentation.rest.user.document

data class AddActorRequest(
        val actorName: String,
        val budgetId: String,
        val accountId: String
)
