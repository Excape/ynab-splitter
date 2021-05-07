package ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports

import ch.excape.ynabsplitter.domain.PushSubscription

data class SubscribePushInput(
        val subscription: PushSubscription
)
