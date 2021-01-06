package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.model.BudgetSummary
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.domain.Budget
import ch.excape.ynabsplitter.domain.BudgetAccount
import org.springframework.beans.factory.annotation.Qualifier

class YnabReadBudgetsRepository(
        @Qualifier("ynabBudgetsApi")
        private val budgetsApi: BudgetsApi
) : ReadBudgetsRepository {
    override fun getBudgets(userId: String): List<Budget> {
        val budgets = budgetsApi.getBudgets(true)
        return budgets.data.budgets.toDomain()
    }
}

private fun List<BudgetSummary>.toDomain(): List<Budget> {
    return map {
        Budget(
                it.name,
                it.id.toString(),
                it.accounts.map { BudgetAccount(it.name, it.id.toString()) })
    }
}