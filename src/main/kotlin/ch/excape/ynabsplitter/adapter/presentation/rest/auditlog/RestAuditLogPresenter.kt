package ch.excape.ynabsplitter.adapter.presentation.rest.auditlog

import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.AuditLogDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AuditLogPresenter
import ch.excape.ynabsplitter.domain.AuditLog

class RestAuditLogPresenter : AuditLogPresenter {
    var presentation: List<AuditLogDocument>? = null

    override fun present(auditLog: List<AuditLog>) {
        presentation = auditLog.map {it.toDocument()}
    }

}

fun AuditLog.toDocument(): AuditLogDocument = AuditLogDocument(
        id,
        date,
        executingActor,
        oldTransactions.associate { it.actor.actorName.name to it.toDocument() },
        newTransactions.associate { it.actor.actorName.name to it.toDocument() }
)