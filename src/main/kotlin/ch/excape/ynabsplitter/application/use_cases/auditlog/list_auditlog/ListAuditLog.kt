package ch.excape.ynabsplitter.application.use_cases.auditlog.list_auditlog

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AllAuditLogsPresenter
import ch.excape.ynabsplitter.application.use_cases.auditlog.list_auditlog.ports.IListAuditLog

class ListAuditLog(private val auditLogRepository: AuditLogRepository) : IListAuditLog {
    override fun executeWith(presenter: AllAuditLogsPresenter) {
        val allAuditLogs = auditLogRepository.getAllAuditLogs()
        presenter.present(allAuditLogs)
    }
}
