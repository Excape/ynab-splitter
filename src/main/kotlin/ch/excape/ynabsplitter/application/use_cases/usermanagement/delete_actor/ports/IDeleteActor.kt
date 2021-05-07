package ch.excape.ynabsplitter.application.use_cases.usermanagement.delete_actor.ports

interface IDeleteActor {
    fun executeWith(input: DeleteActorInput)
}