package ch.excape.ynabsplitter.application.use_cases.auditlog.list_auditlog.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.AllAuditLogsPresenter

interface IListAuditLog {
    fun executeWith(presenter: AllAuditLogsPresenter)
}
