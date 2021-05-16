package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

import ch.excape.ynabsplitter.adapter.presentation.rest.auditlog.document.AuditLogDocument

data class ApproveTransactionResultDocument(
    val success: Boolean,
    val auditLog: AuditLogDocument?
)
