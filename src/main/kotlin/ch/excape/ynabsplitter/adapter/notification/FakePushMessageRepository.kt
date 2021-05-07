package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.domain.PushSubscription
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class FakePushMessageRepository : PushMessageRepository {
    companion object {
        val log: Logger = LogManager.getLogger()
    }

    override fun pushMessage(subscription: PushSubscription, message: String) {
        log.info("Fake Push message: $message")
    }
}