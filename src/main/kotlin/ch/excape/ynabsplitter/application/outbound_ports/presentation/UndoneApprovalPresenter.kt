package ch.excape.ynabsplitter.application.outbound_ports.presentation

interface UndoneApprovalPresenter {
 fun present(result: UndoApprovalResult)
}

data class UndoApprovalResult(
        val success: Boolean,
        val message: String?
) {
 companion object {
  fun success() = UndoApprovalResult(true, null)
  fun failure(message: String?) = UndoApprovalResult(false, message)
 }
}
