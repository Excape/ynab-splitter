package ch.excape.ynabsplitter.domain

data class MatchedTransaction(
        val transactions: MutableList<Transaction>
) {
    val id: String
        get() = concatenateIds(transactions)
}
fun concatenateIds(transactions: MutableList<Transaction>) =
        transactions.joinToString("_") { it.id }
