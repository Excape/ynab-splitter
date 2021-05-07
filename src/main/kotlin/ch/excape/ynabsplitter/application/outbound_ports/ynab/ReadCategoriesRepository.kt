package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.SplitterActor

interface ReadCategoriesRepository {
    fun getCategories(actor: SplitterActor): List<Category>
    fun findCategory(actor: SplitterActor, categoryId: String): Category?
}