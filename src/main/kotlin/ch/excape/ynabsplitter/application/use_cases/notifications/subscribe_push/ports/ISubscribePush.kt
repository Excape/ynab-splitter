package ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports

/**
 * Register or update a push subscription
 */
interface ISubscribePush {
    fun executeWith(input: SubscribePushInput)
}