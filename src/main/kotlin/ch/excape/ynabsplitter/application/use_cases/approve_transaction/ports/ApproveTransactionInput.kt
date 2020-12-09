package ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports

import ch.excape.ynabsplitter.domain.TransactionSplit

data class ApproveTransactionInput(
        val userId: String,
        val transaction: MatchedTransactionIds,
        val executingActor: String,
        val split: TransactionSplit,
        val categories: CategoryPerActor
)

class MatchedTransactionIds(val transactionIdsByActor: Map<String, String>)
