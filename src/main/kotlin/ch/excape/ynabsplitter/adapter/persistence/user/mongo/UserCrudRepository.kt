package ch.excape.ynabsplitter.adapter.persistence.user.mongo

import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.MongoRepository

@Profile("prod")
interface UserCrudRepository : MongoRepository<UserEntity, String> {
    fun findByUserId(userId: String): UserEntity?
}
