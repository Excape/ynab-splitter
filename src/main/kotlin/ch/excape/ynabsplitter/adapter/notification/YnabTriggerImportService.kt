package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabsplitter.application.outbound_ports.notification.TriggerImportService
import ch.excape.ynabsplitter.domain.SplitterActor

class YnabTriggerImportService(
        private val ynabTransactionsApiProvider: YnabTransactionsApiProvider
) : TriggerImportService {
    override fun triggerImport(userId: String, actor: SplitterActor): Int {
        val ynabTransactionsApi = ynabTransactionsApiProvider.provideTransactionsApi(userId)
        val result = ynabTransactionsApi.importTransactions(actor.budgetId)
        return result.data.transactionIds.size
    }
}