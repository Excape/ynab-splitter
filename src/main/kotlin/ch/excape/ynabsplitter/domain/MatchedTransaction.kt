package ch.excape.ynabsplitter.domain

data class MatchedTransaction(
        val transactions: MutableList<Transaction>
)

fun Collection<MatchedTransaction>.containsTransaction(transaction: Transaction) =
        this.flatMap { it.transactions }.contains(transaction)