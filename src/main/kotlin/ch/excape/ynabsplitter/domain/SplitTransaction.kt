package ch.excape.ynabsplitter.domain

data class SplitTransaction(
        val transactions: List<Transaction>,
        val splitQuotient: Int // 0 - 100 (e.g. 50 = 50/50 split)
)