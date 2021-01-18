package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.model.BudgetSummary
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.domain.Budget
import ch.excape.ynabsplitter.domain.BudgetAccount
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

class YnabReadBudgetsRepository(
        @Qualifier("ynabBudgetsApi")
        private val budgetsApi: BudgetsApi
) : ReadBudgetsRepository {
    override fun getBudgets(userId: String): List<Budget> {
        val budgets = budgetsApi.getBudgets(true)
        return budgets.data.budgets.map { it.toDomain() }
    }

    override fun getBudgetById(budgetId: String): Budget? {

        try {
            return budgetsApi.getBudgetById(budgetId, null)
                    .data.budget.toDomain()
        } catch (ex: HttpClientErrorException) {
            if (ex.statusCode == HttpStatus.NOT_FOUND) {
                return null
            }
            throw ex
        }
    }
}

private fun BudgetSummary.toDomain(): Budget {
    return Budget(
                name,
                id.toString(),
                accounts.map { BudgetAccount(it.name, it.id.toString()) })

}