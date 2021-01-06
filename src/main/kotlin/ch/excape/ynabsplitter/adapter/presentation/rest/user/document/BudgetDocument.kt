package ch.excape.ynabsplitter.adapter.presentation.rest.user.document

data class BudgetDocument(
        val name: String,
        val budgetId: String,
        val accounts: List<AccountDocument>
)

data class AccountDocument(
        val name: String,
        val accountId: String
)
