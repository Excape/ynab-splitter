package ch.excape.ynabsplitter.adapter.persistence.notification.mongo

import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.PushSubscription

class MongoSubscriptionRepository(private val crudRepository: MongoSubscriptionCrudRepository) : SubscriptionRepository {
    override fun saveOrUpdateSubscription(subscription: PushSubscription) {
        crudRepository.save(subscription.toEntity())
    }

    override fun getSubscriptionsForUser(userId: String): List<PushSubscription> {
        return crudRepository.findByUserId(userId).map { it.toDomain() }
    }
}

private fun PushSubscription.toEntity() = SubscriptionEntity(
        SubscriptionEntityId(userId, actorName.name),
        userId,
        endpoint,
        publicKey,
        authSecret
)

private fun SubscriptionEntity.toDomain() = PushSubscription(
        userId, ActorName(id.actorName), endpoint, publicKey, authSecret
)
