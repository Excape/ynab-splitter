package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_budgets

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.BudgetsPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_budgets.ports.IGetBudgets
import ch.excape.ynabsplitter.domain.User

class GetBudgets(
        private val userRepository: UserRepository,
        private val budgetsRepository: ReadBudgetsRepository
) : IGetBudgets {

    override fun executeWith(userId: String, presenter: BudgetsPresenter) {
        val user = getUser(userId)
        val budgets = budgetsRepository.getBudgets(user.userId)
        presenter.present(budgets)
    }

    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("user with id $userId not found")

}