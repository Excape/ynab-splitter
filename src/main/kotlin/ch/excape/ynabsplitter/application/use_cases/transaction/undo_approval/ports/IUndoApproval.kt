package ch.excape.ynabsplitter.application.use_cases.transaction.undo_approval.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.UndoneApprovalPresenter

interface IUndoApproval {
    fun executeWith(input: UndoApprovalInput, presenter: UndoneApprovalPresenter)
}