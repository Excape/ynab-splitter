package ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document

data class UndoApprovalRequest(val auditLogId: String)

data class UndoApprovalResultDocument(val success: Boolean, val message: String?)