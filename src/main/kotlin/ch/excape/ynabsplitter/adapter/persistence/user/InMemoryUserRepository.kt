package ch.excape.ynabsplitter.adapter.persistence.user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings

class InMemoryUserRepository : UserRepository {
    override fun getUser(userId: String): User {
        return User(
                userId = userId,
                settings = UserSettings(listOf(
                        SplitterActor("Anusha", "fef04036-5381-46ce-978d-375507a9e26e", "d4a3b145-e1c7-4ccf-914e-b80d16bb82f6"),
                        SplitterActor("Bartholomew", "710d9d09-a1e8-4951-84cc-a5c261947839", "9bd454eb-fd28-4472-a629-11df1c0d2272")
                ))
        )
    }

    override fun createUser(user: User) {
        // noop
    }
}