package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.Budget

interface BudgetsPresenter {
    fun present(budgets: List<Budget>)
}
