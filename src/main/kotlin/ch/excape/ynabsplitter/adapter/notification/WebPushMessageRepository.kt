package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SubscriptionDocument
import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.domain.PushSubscription
import nl.martijndwars.webpush.Notification
import nl.martijndwars.webpush.PushService
import nl.martijndwars.webpush.Utils
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security

class WebPushMessageRepository(
        private val publicKey: String,
        private val privateKey: String
) : PushMessageRepository {

    private val pushService = PushService()

    init {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(BouncyCastleProvider())
        }

        // TODO configure these in system properties
        pushService.publicKey = Utils.loadPublicKey(publicKey)
        pushService.privateKey = Utils.loadPrivateKey(privateKey)
    }

    override fun pushMessage(subscription: PushSubscription, message: String) {
        val notification = buildNotification(subscription, message)
        val response = pushService.send(notification)
        if (response.statusLine.statusCode != 201) {
            throw IllegalStateException("Failed to send notification: ${response.statusLine}")
        }
    }

    private fun buildNotification(subscription: PushSubscription, message: String) =
            Notification(
                    subscription.endpoint,
                    subscription.publicKey,
                    subscription.authSecret,
                    message
            )
}