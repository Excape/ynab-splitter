package ch.excape.ynabsplitter.application.use_cases.notifications.unsubscribe_push

import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.unsubscribe_push.ports.IUnsubscribePush
import ch.excape.ynabsplitter.application.use_cases.notifications.unsubscribe_push.ports.UnsubscribePushInput

class UnsubscribePush(
        private val subscriptionRepository: SubscriptionRepository,
) : IUnsubscribePush {
    override fun executeWith(input: UnsubscribePushInput) {
        val subscription = input.subscription
        subscriptionRepository.removeSubscription(subscription)
    }
}