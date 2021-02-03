package ch.excape.ynabsplitter.application.use_cases.notifications.unsubscribe_push.ports

interface IUnsubscribePush {
    fun executeWith(input: UnsubscribePushInput)
}