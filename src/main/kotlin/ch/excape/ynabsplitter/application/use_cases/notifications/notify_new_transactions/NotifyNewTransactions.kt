package ch.excape.ynabsplitter.application.use_cases.notifications.notify_new_transactions

import ch.excape.ynabsplitter.adapter.notification.AccessTokenNotAvailableException
import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.application.outbound_ports.notification.TriggerImportService
import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.notify_new_transactions.ports.INotifyNewTransactions
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.PushSubscription
import ch.excape.ynabsplitter.domain.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class NotifyNewTransactions(
        private val userRepository: UserRepository,
        private val subscriptionRepository: SubscriptionRepository,
        private val pushMessageRepository: PushMessageRepository,
        private val triggerImportService: TriggerImportService
) : INotifyNewTransactions {

    companion object {
        val log: Logger = LogManager.getLogger()
    }

    override fun execute() {
        val subscriptions = subscriptionRepository.getAllSubscriptions()
        subscriptions.forEach {
            importAndNotify(it)
        }
    }

    private fun importAndNotify(it: PushSubscription) {
        try {
            val count = importTransactions(it)

            if (count > 0) {
                notifyUser(it, count)
            }
        } catch (ex: AccessTokenNotAvailableException) {
            log.info("No token available for user ${it.userId}, skipping this notification")
        }
    }

    private fun importTransactions(subscription: PushSubscription): Int {
        val user = getUser(subscription.userId)
        val actor = extractActor(user, subscription.actorName)

        val importCountByActor = user.settings.actors.associateWith {
            triggerImportService.triggerImport(user.userId, it)
        }
        return importCountByActor[actor]!!
    }

    private fun notifyUser(subscription: PushSubscription, count: Int) {
        try {
            pushMessageRepository.pushMessage(subscription, "$count new transactions to approve")
        } catch (ex: java.lang.IllegalStateException) {
            log.warn("Could not notify user: ${ex.message}, removing subscription")
            subscriptionRepository.removeSubscription(subscription)
        }
    }

    private fun extractActor(user: User, actorName: ActorName) =
            user.settings.actors.find { it.actorName == actorName }
                    ?: throw IllegalArgumentException("actor ${actorName.name} not configured for user ${user.userId}")

    private fun getUser(userId: String) =
            userRepository.getUser(userId) ?: throw
            IllegalStateException("User with ID $userId not found in database")
}