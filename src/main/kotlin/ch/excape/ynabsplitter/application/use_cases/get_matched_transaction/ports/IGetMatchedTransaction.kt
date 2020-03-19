package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter

interface IGetMatchedTransaction {
    fun executeWith(input: GetMatchedTransactionInput, presenter: MatchedTransactionPresenter)
}