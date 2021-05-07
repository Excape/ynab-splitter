package ch.excape.ynabsplitter.adapter.persistence.notification.mongo

import org.springframework.data.repository.CrudRepository

interface MongoSubscriptionCrudRepository
    : CrudRepository<SubscriptionEntity, SubscriptionEntityId> {
        fun findByUserId(userId: String): List<SubscriptionEntity>
}