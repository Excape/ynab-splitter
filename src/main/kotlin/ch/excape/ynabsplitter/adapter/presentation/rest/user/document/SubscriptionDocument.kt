package ch.excape.ynabsplitter.adapter.presentation.rest.user.document

import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.PushSubscription

data class SubscribePushRequest(
        val actorName: String,
        val subscription: SubscriptionDocument
) {
    fun toSubscription(userId: String): PushSubscription {
        return PushSubscription(
                userId,
                ActorName(actorName),
                subscription.endpoint,
                subscription.keys.p256dh,
                subscription.keys.auth
        )
    }
}

// this structure is defined by https://developer.mozilla.org/en-US/docs/Web/API/PushSubscription
data class SubscriptionDocument(
        val endpoint: String,
        val keys: SubscriptionKeysDocument
)

data class SubscriptionKeysDocument(
        val auth: String,
        val p256dh: String
)