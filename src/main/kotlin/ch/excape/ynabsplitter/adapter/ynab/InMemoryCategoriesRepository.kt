package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.SplitterActor
import java.lang.IllegalArgumentException

class InMemoryCategoriesRepository : ReadCategoriesRepository {
    private val categoriesAnusha = listOf(
            Category("catGroceries", "Groceries", "Real expenses", 10500),
            Category("catCoffee", "Coffee", "Real expenses", 1534),
            Category("cat3", "Rent", "Real expenses", 40000),
            Category("cat4", "Vacation", "Just for Fun", 33400),
            Category("cat5", "Presents", "Just for Fun", 5643)
    )

    private val categoriesBob = listOf(
            Category("catGroceries", "Groceries", "True expenses", 20040),
            Category("catCoffee", "Coffee", "True expenses", 2304),
            Category("cat8", "Household", "True expenses", 5498),
            Category("cat9", "Next Move", "Funding Goals", 68745),
            Category("cat10", "Vienna", "Funding Goals", 43278)
    )

    override fun getCategories(actor: SplitterActor): List<Category> {
        return when (actor.name) {
            "Anusha" -> categoriesAnusha
            "Bartholomew" -> categoriesBob
            else -> throw IllegalArgumentException("${actor.name} not known for in memory categories")
        }
    }

    override fun findCategory(actor: SplitterActor, categoryId: String): Category? {
        return when (actor.name) {
            "Anusha" -> categoriesAnusha.find { it.id == categoryId }
            "Bartholomew" -> categoriesBob.find { it.id == categoryId }
            else -> throw IllegalArgumentException("${actor.name} not known for in memory categories")
        }
    }
}