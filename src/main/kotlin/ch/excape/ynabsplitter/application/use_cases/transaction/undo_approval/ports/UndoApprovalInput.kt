package ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.ports

data class UndoApprovalInput(
        val userId: String,
        val auditLogId: String,
)
