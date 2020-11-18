package ch.excape.ynabsplitter.application.outbound_ports.persistence

import ch.excape.ynabsplitter.domain.User

interface UserRepository {
    fun getOrCreateUser(userId: String) : User
}