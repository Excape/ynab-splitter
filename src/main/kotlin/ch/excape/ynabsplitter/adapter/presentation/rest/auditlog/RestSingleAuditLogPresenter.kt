package ch.excape.ynabsplitter.adapter.presentation.rest.auditlog

import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.AuditLogDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.SingleAuditLogPresenter
import ch.excape.ynabsplitter.domain.AuditLog

class RestSingleAuditLogPresenter : SingleAuditLogPresenter {
    var presentation: AuditLogDocument? = null
    
    override fun present(auditLog: AuditLog) {
        presentation = auditLog.toDocument()
    }
}
