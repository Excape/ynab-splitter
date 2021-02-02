package ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push

import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports.ISubscribePush
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports.SubscribePushInput

class SubscribePush(
        private val subscriptionRepository: SubscriptionRepository,
        // TODO move to another use case
        private val pushMessageRepository: PushMessageRepository
) : ISubscribePush {
    override fun executeWith(input: SubscribePushInput) {
        val subscription = input.subscription
        subscriptionRepository.saveOrUpdateSubscription(subscription)
        pushMessageRepository.pushMessage(subscription, "You are now subscribed to push notifications" )
    }
}