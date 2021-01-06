package ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.ApproveTransactionPresenter

interface IApproveTransaction {
    fun executeWith(input: ApproveTransactionInput, presenter: ApproveTransactionPresenter)
}