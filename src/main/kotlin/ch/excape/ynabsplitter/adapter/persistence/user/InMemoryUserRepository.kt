package ch.excape.ynabsplitter.adapter.persistence.user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings

class InMemoryUserRepository : UserRepository {
    override fun getOrCreateUser(userId: String): User {
        return User(
                userId = userId,
                settings = UserSettings(listOf(
                        SplitterActor("Alice", "FAKE-BUDGET-ID", "FAKE-ACCOUNT-ID"),
                        SplitterActor("Bob", "FAKE-BUDGET-ID", "FAKE-ACCOUNT-ID")
                ))
        )
    }
}