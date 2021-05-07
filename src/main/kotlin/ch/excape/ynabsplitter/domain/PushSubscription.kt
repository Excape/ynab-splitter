package ch.excape.ynabsplitter.domain

data class PushSubscription(
        val userId: String,
        val actorName: ActorName,
        val endpoint: String,
        val publicKey: String,
        val authSecret: String
)