package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.domain.Budget
import ch.excape.ynabsplitter.domain.BudgetAccount

class InMemoryReadBudgetsRepository : ReadBudgetsRepository {

    val budgets = listOf(
            Budget("My Budget", "budget-id",
                    listOf(
                            BudgetAccount("Chase card", "chase-id"),
                            BudgetAccount("Bank account", "bank-id")
                    )
            ),
            Budget("Other Budget", "other-budget-id",
                    listOf(
                            BudgetAccount("Shared Chase card", "chase-2-id"),
                            BudgetAccount("My Bank Account", "bank-2-id")
                    )
            )
    )

    override fun getBudgets(userId: String): List<Budget> = budgets

    override fun getBudgetById(budgetId: String): Budget? =
            budgets.find { it.budgetId == budgetId }
}