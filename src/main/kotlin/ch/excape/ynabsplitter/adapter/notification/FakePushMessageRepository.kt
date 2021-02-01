package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.domain.PushSubscription

class FakePushMessageRepository : PushMessageRepository {
    override fun pushMessage(subscription: PushSubscription, message: String) {
        println("Push message: $message")
    }
}