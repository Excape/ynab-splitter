package ch.excape.ynabsplitter.application.outbound_ports.persistence

import ch.excape.ynabsplitter.domain.User

interface UserRepository {
    fun getUser(userId: String) : User?

    fun createUser(user: User)

    fun updateUser(user: User)
}