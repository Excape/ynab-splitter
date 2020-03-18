package ch.excape.ynabsplitter.application.use_cases.get_categories

import ch.excape.ynabsplitter.application.outbound_ports.presentation.CategoryListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.GetCategoriesInput
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.IGetCategories

class GetCategories(private val readCategoriesRepository: ReadCategoriesRepository): IGetCategories {
    override fun executeWith(input: GetCategoriesInput, presenter: CategoryListPresenter) {
        val categories = readCategoriesRepository.getCategories(input.actor)
        presenter.present(categories)
    }
}