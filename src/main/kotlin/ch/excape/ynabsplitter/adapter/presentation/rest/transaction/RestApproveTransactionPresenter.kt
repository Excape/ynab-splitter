package ch.excape.ynabsplitter.adapter.presentation.rest.transaction

import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionResult

class RestApproveTransactionPresenter : ApproveTransactionPresenter {
    var presentation: ApproveTransactionResult? = null
    override fun present(result: ApproveTransactionResult) {
        presentation = result
    }
}