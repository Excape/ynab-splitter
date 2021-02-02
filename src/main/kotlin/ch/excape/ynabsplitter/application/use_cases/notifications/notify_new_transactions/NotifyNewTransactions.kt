package ch.excape.ynabsplitter.application.use_cases.notifications.notify_new_transactions

import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.notify_new_transactions.ports.INotifyNewTransactions
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.PushSubscription
import ch.excape.ynabsplitter.domain.User

class NotifyNewTransactions(
        private val userRepository: UserRepository,
        private val subscriptionRepository: SubscriptionRepository,
        private val pushMessageRepository: PushMessageRepository,
        private val readTransactionsRepository: ReadTransactionsRepository
) : INotifyNewTransactions {

    override fun execute() {
        val subscriptions = subscriptionRepository.getAllSubscriptions()
        subscriptions.forEach {
            val count = importTransactions(it)
            if (count > 0) {
                notifyUser(it, count)
            }
        }
    }

    private fun importTransactions(subscription: PushSubscription): Int {
        val user = getUser(subscription.userId)
        val actor = extractActor(user, subscription.actorName)

        val transactionCount = readTransactionsRepository.triggerTransactionImport(actor)
        return transactionCount
    }

    private fun notifyUser(subscription: PushSubscription, count: Int) {
        pushMessageRepository.pushMessage(subscription, "$count new transactions to approve")
    }

    private fun extractActor(user: User, actorName: ActorName) =
            user.settings.actors.find { it.actorName == actorName } ?:
                throw IllegalArgumentException("actor ${actorName.name} not configured for user ${user.userId}")

    private fun getUser(userId: String) =
            userRepository.getUser(userId) ?: throw
            IllegalStateException("User with ID $userId not found in database")
}