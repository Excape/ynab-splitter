package ch.excape.ynabsplitter.application.use_cases.usermanagement.delete_actor

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.use_cases.usermanagement.delete_actor.ports.DeleteActorInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.delete_actor.ports.IDeleteActor
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User

class DeleteActor(private val userRepository: UserRepository) : IDeleteActor {
    override fun executeWith(input: DeleteActorInput) {
        val user = getUser(input.userId)
        val updatedUser = removeActorFromUser(user, input.name)
        userRepository.updateUser(updatedUser)
    }

    private fun removeActorFromUser(user: User, name: String): User {
        val actorsCopy = user.settings.actors.toMutableList()
        val actorToRemove = findActor(actorsCopy, name)
        actorsCopy.remove(actorToRemove)
        return user.copy(settings = user.settings.copy(actors = actorsCopy))
    }

    private fun findActor(actors: List<SplitterActor>, actorName: String) =
            actors.find { it.actorName.name == actorName }
                    ?: throw IllegalArgumentException("Actor with name $actorName not configured")

    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("user with id $userId not found")

}