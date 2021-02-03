package ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push

import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports.ISubscribePush
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports.SubscribePushInput

class SubscribePush(
        private val subscriptionRepository: SubscriptionRepository,
) : ISubscribePush {
    override fun executeWith(input: SubscribePushInput) {
        val subscription = input.subscription
        subscriptionRepository.saveOrUpdateSubscription(subscription)
    }
}