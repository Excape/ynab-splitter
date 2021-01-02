package ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports

data class AddActorInput(
        val userId: String,
        val name: String,
        val budgetId: String,
        val accountId: String
)