package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction

fun matchTransactions(transactions: List<Transaction>) : List<MatchedTransaction> {
    val matchedTransactions = mutableListOf<MatchedTransaction>()
    transactions.forEach { transaction ->
        matchTransaction(matchedTransactions, transaction)
    }
    return matchedTransactions.toList()
}

private fun matchTransaction(matchedTransactions: MutableList<MatchedTransaction>, transaction: Transaction) {
    val matchedTransaction = getExistingMatch(matchedTransactions, transaction)
    if (matchedTransaction == null) {
        val newMatch = MatchedTransaction(mutableListOf(transaction))
        matchedTransactions.add(newMatch)
    } else {
        matchedTransaction.transactions.add(transaction)
    }
}

private fun getExistingMatch(matchedTransactions: MutableList<MatchedTransaction>,
                             transaction: Transaction): MatchedTransaction? {
    return matchedTransactions
            .firstOrNull {
                hasMatchingTransactions(it, transaction) && doesNotContainActor(it, transaction)
            }
}

private fun hasMatchingTransactions(matchedTransaction: MatchedTransaction, transaction: Transaction) =
        matchedTransaction.transactions.any { t -> t.matches(transaction) }

private fun doesNotContainActor(matchingTransaction: MatchedTransaction, transaction: Transaction) =
        matchingTransaction.transactions.none { t -> t.actor == transaction.actor }
