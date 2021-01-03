package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.BudgetDocument
import ch.excape.ynabsplitter.adapter.rest.document.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.BudgetsPresenter
import ch.excape.ynabsplitter.domain.Budget

class RestBudgetsPresenter: BudgetsPresenter {
    var presentation: List<BudgetDocument>? = null
    override fun present(budgets: List<Budget>) {
        presentation = budgets.map { it.toDocument() }
    }

}
