package ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorResult
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports.AddActorInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports.IAddActor
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User

class AddActor(private val userRepository: UserRepository) : IAddActor {
    override fun executeWith(input: AddActorInput, presenter: AddActorPresenter) {
        val user = getUser(input.userId)
        val actor = buildActor(input)

        if (actorAlreadyExists(user, actor)) {
            val result = AddActorResult.failure("Actor already exists")
            presenter.present(result)
            return
        }

        val updatedUser = addActorToUser(user, actor)

        userRepository.updateUser(updatedUser)
        presenter.present(AddActorResult.success(actor))
    }

    private fun buildActor(input: AddActorInput): SplitterActor =
            SplitterActor(ActorName(input.name), input.budgetId, input.accountId)

    private fun addActorToUser(user: User, actor: SplitterActor): User {
        return user.copy(
                settings = user.settings.copy(
                        actors = user.settings.actors + actor
                )
        )
    }

    private fun actorAlreadyExists(user: User, actor: SplitterActor): Boolean {
        return user.settings.actors.any { it.actorName == actor.actorName }
    }

    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("user with id $userId not found")

}