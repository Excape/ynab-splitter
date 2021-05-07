package ch.excape.ynabsplitter.adapter.presentation.rest.transaction

import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.CategoryDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.CategoryListPresenter
import ch.excape.ynabsplitter.domain.Category

class RestCategoryListPresenter : CategoryListPresenter {

    var presentation: List<CategoryDocument>? = null

    override fun present(categories: List<Category>) {
      presentation = categories.map { it.toDocument() }
    }
}

fun Category.toDocument(): CategoryDocument = CategoryDocument(id, name ?: "", group, balance)
