package ch.excape.ynabsplitter.adapter.persistence.notification.mongo

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "subscriptions")
data class SubscriptionEntity(
        val id: SubscriptionEntityId,
        val userId: String,
        val endpoint: String,
        val publicKey: String,
        val authSecret: String,

)

data class SubscriptionEntityId(
        val userId: String,
        val actorName: String
)
