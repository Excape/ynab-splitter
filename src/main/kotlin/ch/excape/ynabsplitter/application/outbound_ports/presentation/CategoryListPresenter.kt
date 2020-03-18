package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.Category

interface CategoryListPresenter {
    fun present(categories: List<Category>)
}