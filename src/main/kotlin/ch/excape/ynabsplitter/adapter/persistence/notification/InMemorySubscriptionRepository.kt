package ch.excape.ynabsplitter.adapter.persistence.notification

import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.domain.PushSubscription

class InMemorySubscriptionRepository : SubscriptionRepository {

    private val subscriptions: MutableMap<String, PushSubscription> = mutableMapOf()
    override fun getAllSubscriptions(): List<PushSubscription> {
        return subscriptions.values.toList()
    }

    override fun saveOrUpdateSubscription(subscription: PushSubscription) {
        subscriptions.put(createKey(subscription), subscription)
    }

    private fun createKey(subscription: PushSubscription) =
            "${subscription.userId}_${subscription.actorName}"

    override fun removeSubscription(subscription: PushSubscription) {
        subscriptions.remove(createKey(subscription))
    }

    override fun getSubscriptionsForUser(userId: String): List<PushSubscription> {
        return subscriptions
                .filter { it.key.contains(userId) }
                .map { it.value }
    }
}