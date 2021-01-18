package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_actors

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.GetActorsPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_actors.ports.IGetActors
import ch.excape.ynabsplitter.domain.ActorWithBudget
import ch.excape.ynabsplitter.domain.Budget
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User

class GetActors(
        private val userRepository: UserRepository,
        private val budgetsRepository: ReadBudgetsRepository
) : IGetActors {
    override fun executeWith(userId: String, presenter: GetActorsPresenter) {
        val user = getUser(userId)
        val actorWithBudget = user.settings.actors.map { it.toActorWithBudget() }
        presenter.present(actorWithBudget)
    }

    private fun SplitterActor.toActorWithBudget(): ActorWithBudget {
        val budget = fetchBudget(budgetId)
        val account = extractAccount(budget, accountId)
        return ActorWithBudget(
                actorName,
                budget.name,
                account.name
        )
    }

    private fun extractAccount(budget: Budget, accountId: String) =
            budget.accounts.find { it.accountId == accountId } ?:
                throw IllegalArgumentException("Budget ${budget.budgetId} does not contain account with ID $accountId")

    private fun fetchBudget(budgetId: String) =
            budgetsRepository.getBudgetById(budgetId) ?: throw IllegalArgumentException("Budget $budgetId not found")

    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("user with id $userId not found")
}
