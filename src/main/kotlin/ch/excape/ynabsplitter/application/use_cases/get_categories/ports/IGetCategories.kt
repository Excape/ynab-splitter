package ch.excape.ynabsplitter.application.use_cases.get_categories.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.CategoryListPresenter

interface IGetCategories {
    fun executeWith(input: GetCategoriesInput, presenter: CategoryListPresenter)
}