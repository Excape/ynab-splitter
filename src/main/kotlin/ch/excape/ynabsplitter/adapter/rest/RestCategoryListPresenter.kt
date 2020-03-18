package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.application.outbound_ports.presentation.CategoryListPresenter
import ch.excape.ynabsplitter.domain.Category

class RestCategoryListPresenter : CategoryListPresenter {

    var presentation: List<Category>? = null

    override fun present(categories: List<Category>) {
      presentation = categories
    }
}