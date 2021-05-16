package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.AuditLog

interface SingleAuditLogPresenter {
    fun present(auditLog: AuditLog)
}
