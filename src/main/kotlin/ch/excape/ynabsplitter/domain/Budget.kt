package ch.excape.ynabsplitter.domain

data class Budget(
        val name: String,
        val budgetId: String,
        val accounts: List<BudgetAccount>
)

data class BudgetAccount(
        val name: String,
        val accountId: String
)