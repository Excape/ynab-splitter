package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_budgets.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.BudgetsPresenter

interface IGetBudgets {
    fun executeWith(userId: String, presenter: BudgetsPresenter)
}