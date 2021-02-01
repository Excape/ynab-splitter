package ch.excape.ynabsplitter.application.outbound_ports.persistence

import ch.excape.ynabsplitter.domain.PushSubscription

interface SubscriptionRepository {
    fun saveOrUpdateSubscription(subscription: PushSubscription)
    fun getSubscriptionsForUser(userId: String): List<PushSubscription>
}
