package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category

interface ReadCategoriesRepository {
    fun getCategories(actor: Actor): List<Category>
}