package ch.excape.ynabsplitter.adapter.persistence.notification.mongo

import org.springframework.context.annotation.Profile
import org.springframework.data.repository.CrudRepository

@Profile("prod")
interface MongoSubscriptionCrudRepository
    : CrudRepository<SubscriptionEntity, SubscriptionEntityId> {
    fun findByUserId(userId: String): List<SubscriptionEntity>
}
