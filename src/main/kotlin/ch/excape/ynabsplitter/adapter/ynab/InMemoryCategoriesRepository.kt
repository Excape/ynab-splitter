package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category

class InMemoryCategoriesRepository : ReadCategoriesRepository {
    private val categoriesRobin = listOf(
            Category("catGroceries", "Groceries", "Real expenses", 10500),
            Category("catCoffee", "Coffee", "Real expenses", 1534),
            Category("cat3", "Rent", "Real expenses", 40000),
            Category("cat4", "Vacation", "Just for Fun", 33400),
            Category("cat5", "Presents", "Just for Fun", 5643)
    )

    private val categoriesSophie = listOf(
            Category("catGroceries", "Groceries", "True expenses", 20040),
            Category("catCoffee", "Coffee", "True expenses", 2304),
            Category("cat8", "Household", "True expenses", 5498),
            Category("cat9", "Next Move", "Funding Goals", 68745),
            Category("cat10", "Vienna", "Funding Goals", 43278)
    )

    override fun getCategories(actor: Actor): List<Category> {
        return when (actor) {
            Actor.SOPHIE -> categoriesSophie
            Actor.ROBIN -> categoriesRobin
        }
    }

    override fun findCategory(actor: Actor, categoryId: String): Category? {
        return when (actor) {
            Actor.ROBIN -> categoriesRobin.find { it.id == categoryId }
            Actor.SOPHIE -> categoriesSophie.find { it.id == categoryId }
        }
    }
}