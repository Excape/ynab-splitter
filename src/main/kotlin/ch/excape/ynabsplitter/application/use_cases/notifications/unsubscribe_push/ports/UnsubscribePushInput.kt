package ch.excape.ynabsplitter.application.use_cases.notifications.unsubscribe_push.ports

import ch.excape.ynabsplitter.domain.PushSubscription

data class UnsubscribePushInput(
        val subscription: PushSubscription
)
