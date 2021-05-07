package ch.excape.ynabsplitter.adapter.persistence.user.mongo

import org.springframework.data.mongodb.repository.MongoRepository

interface UserCrudRepository : MongoRepository<UserEntity, String> {
    fun findByUserId(userId: String): UserEntity?
}