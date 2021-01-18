package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.Budget

interface ReadBudgetsRepository {
    fun getBudgets(userId: String): List<Budget>
    fun getBudgetById(budgetId: String): Budget?
}