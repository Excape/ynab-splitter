package ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.TransactionSplit

data class ApproveTransactionInput(
        val transaction: MatchedTransaction,
        val executingActor: Actor,
        val split: TransactionSplit
)
