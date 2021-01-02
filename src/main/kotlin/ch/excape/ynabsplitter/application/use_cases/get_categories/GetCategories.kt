package ch.excape.ynabsplitter.application.use_cases.get_categories

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.CategoryListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.GetCategoriesInput
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.IGetCategories
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User

class GetCategories(
        private val readCategoriesRepository: ReadCategoriesRepository,
        private val userRepository: UserRepository): IGetCategories {
    override fun executeWith(input: GetCategoriesInput, presenter: CategoryListPresenter) {
        val user = getUser(input.userId)
        val actor = getActor(user, input.actorName)
        val categories = readCategoriesRepository.getCategories(actor)
        presenter.present(categories)
    }
    private fun getUser(userId: String): User =
            userRepository.getUser(userId) ?: throw IllegalStateException("User with id $userId not found")

    private fun getActor(user: User, actorName: String): SplitterActor {
       return user.settings.actors.find { it.actorName.name == actorName }
               ?: throw IllegalArgumentException("Actor $actorName not configured for user ${user.userId}")
    }
}