package ch.excape.ynabsplitter.adapter.persistence.user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.domain.User

class MongoUserRepository(private val mongoRepo: UserCrudRepository) : UserRepository {
    override fun getUser(userId: String): User? =
            mongoRepo.findByUserId(userId)?.toDomain()

    override fun createUser(user: User) {
         mongoRepo.save(user.toEntity())
    }

    override fun updateUser(user: User) {
        mongoRepo.save(user.toEntity())
    }
}