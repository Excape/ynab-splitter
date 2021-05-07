package ch.excape.ynabsplitter.application.outbound_ports.notification

import ch.excape.ynabsplitter.domain.SplitterActor

interface TriggerImportService {
    fun triggerImport(userId: String, actor: SplitterActor): Int
}