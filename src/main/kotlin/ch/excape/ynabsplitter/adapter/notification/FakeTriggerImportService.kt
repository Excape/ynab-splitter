package ch.excape.ynabsplitter.adapter.notification

import ch.excape.ynabsplitter.application.outbound_ports.notification.TriggerImportService
import ch.excape.ynabsplitter.domain.SplitterActor

class FakeTriggerImportService: TriggerImportService {
    override fun triggerImport(userId: String, actor: SplitterActor): Int {
        // noop
        return 0
    }
}