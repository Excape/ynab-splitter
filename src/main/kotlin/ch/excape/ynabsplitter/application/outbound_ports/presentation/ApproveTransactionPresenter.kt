package ch.excape.ynabsplitter.application.outbound_ports.presentation

interface ApproveTransactionPresenter {
    fun present(result: ApproveTransactionResult)
}

data class ApproveTransactionResult(val success: Boolean, val auditLogId: String?)
