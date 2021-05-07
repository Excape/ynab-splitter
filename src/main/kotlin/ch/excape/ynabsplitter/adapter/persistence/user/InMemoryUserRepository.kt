package ch.excape.ynabsplitter.adapter.persistence.user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings

class InMemoryUserRepository : UserRepository {
    override fun getUser(userId: String): User {
        return User(
                userId = userId,
                settings = UserSettings(listOf(
                        SplitterActor(ActorName("Anusha"), "budget-id", "chase-id"),
                        SplitterActor(ActorName("Bartholomew"), "other-budget-id", "chase-2-id")
                ))
        )
    }

    override fun createUser(user: User) {
        // noop
    }

    override fun updateUser(user: User) {
        // noop
    }
}