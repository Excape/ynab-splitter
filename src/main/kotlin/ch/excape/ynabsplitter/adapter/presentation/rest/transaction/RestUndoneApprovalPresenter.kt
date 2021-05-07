package ch.excape.ynabsplitter.adapter.presentation.rest.transaction

import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.UndoApprovalResultDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UndoApprovalResult
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UndoneApprovalPresenter

class RestUndoneApprovalPresenter: UndoneApprovalPresenter {
    var presentation: UndoApprovalResultDocument? = null

    override fun present(result: UndoApprovalResult) {
        presentation = result.toDocument()
    }

}

private fun UndoApprovalResult.toDocument(): UndoApprovalResultDocument {
    return UndoApprovalResultDocument(success, message)
}
