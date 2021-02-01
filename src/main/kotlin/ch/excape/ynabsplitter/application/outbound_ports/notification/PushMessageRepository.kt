package ch.excape.ynabsplitter.application.outbound_ports.notification

import ch.excape.ynabsplitter.domain.PushSubscription

interface PushMessageRepository {
    fun pushMessage(subscription: PushSubscription, message: String)
}