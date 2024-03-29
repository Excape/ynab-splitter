package ch.excape.ynabsplitter.rest.scheduler

import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.application.outbound_ports.notification.TriggerImportService
import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.notify_new_transactions.NotifyNewTransactions
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@Profile("prod")
class ImportAndNotifyScheduler(
        private val userRepository: UserRepository,
        private val subscriptionRepository: SubscriptionRepository,
        private val pushMessageRepository: PushMessageRepository,
        private val triggerImportService: TriggerImportService
) {

    companion object {
        val log: Logger = LogManager.getLogger()
    }

    @Scheduled(fixedDelay = 600_000)
    fun importAndNotify() {
        log.info("Triggering 'import and notify'")
        val executor = NotifyNewTransactions(userRepository, subscriptionRepository,
                pushMessageRepository, triggerImportService)

        executor.execute()
    }
}
