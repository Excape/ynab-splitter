package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.model.CategoryGroupWithCategories
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.SplitterActor
import org.springframework.beans.factory.annotation.Qualifier

class YnabCategoriesRepository(
        @Qualifier("ynabCategoriesApi")
        private val categoriesApi: CategoriesApi
) : ReadCategoriesRepository {

    private val FILTERED_GROUPS = listOf(
            "Internal Master Category",
            "Credit Card Payments"
    )

    override fun getCategories(actor: SplitterActor): List<Category> =
            retrieveCategoryGroups(actor)
                    .filter { !it.isDeleted }
                    .filter(this::isNotFilteredOutGroup)
                    .map(this::toCategories)
                    .flatten()

    override fun findCategory(actor: SplitterActor, categoryId: String): Category {
        val category = categoriesApi.getCategoryById(actor.budgetId, categoryId).data.category
        return category.toCategory()
    }

    private fun toCategories(categoryGroup: CategoryGroupWithCategories): List<Category> =
            categoryGroup.categories
                    .filter { !it.isDeleted }
                    .map { it.toCategory(categoryGroup.name) }

    private fun isNotFilteredOutGroup(group: CategoryGroupWithCategories) = !FILTERED_GROUPS.contains(group.name)

    private fun retrieveCategoryGroups(actor: SplitterActor) =
            categoriesApi.getCategories(actor.budgetId, null)
                    .data
                    .categoryGroupWithCategoriesList
}

