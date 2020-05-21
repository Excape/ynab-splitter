package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.AuditLogDocument
import ch.excape.ynabsplitter.adapter.rest.document.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AuditLogPresenter
import ch.excape.ynabsplitter.domain.AuditLog

class RestAuditLogPresenter : AuditLogPresenter {
    var presentation: List<AuditLogDocument>? = null

    override fun present(auditLog: List<AuditLog>) {
        presentation = auditLog.map {it.toDocument()}
    }

}