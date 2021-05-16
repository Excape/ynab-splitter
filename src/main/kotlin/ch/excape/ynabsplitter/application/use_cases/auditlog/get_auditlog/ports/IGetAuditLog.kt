package ch.excape.ynabsplitter.application.use_cases.auditlog.get_auditlog.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.SingleAuditLogPresenter

interface IGetAuditLog {
    fun executeWith(auditLogId: String, presenter: SingleAuditLogPresenter)
}
