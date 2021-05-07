package ch.excape.ynabsplitter.adapter.presentation.rest.user

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.AccountDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.BudgetDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.BudgetsPresenter
import ch.excape.ynabsplitter.domain.Budget
import ch.excape.ynabsplitter.domain.BudgetAccount

class RestBudgetsPresenter: BudgetsPresenter {
    var presentation: List<BudgetDocument>? = null
    override fun present(budgets: List<Budget>) {
        presentation = budgets.map { it.toDocument() }
    }

}

fun Budget.toDocument(): BudgetDocument =
        BudgetDocument(name, budgetId, accounts.map { it.toDocument() })

fun BudgetAccount.toDocument(): AccountDocument =
        AccountDocument(name, accountId)
