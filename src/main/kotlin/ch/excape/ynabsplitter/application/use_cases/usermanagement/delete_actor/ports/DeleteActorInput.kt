package ch.excape.ynabsplitter.application.use_cases.usermanagement.delete_actor.ports

data class DeleteActorInput(
        val userId: String,
        val name: String
)