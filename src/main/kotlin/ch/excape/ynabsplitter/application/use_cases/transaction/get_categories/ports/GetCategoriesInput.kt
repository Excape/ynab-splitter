package ch.excape.ynabsplitter.application.use_cases.transaction.get_categories.ports

data class GetCategoriesInput(
        val userId: String,
        val actorName: String
)
