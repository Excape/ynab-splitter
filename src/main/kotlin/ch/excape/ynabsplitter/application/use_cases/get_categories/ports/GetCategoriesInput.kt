package ch.excape.ynabsplitter.application.use_cases.get_categories.ports

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.SplitterActor

data class GetCategoriesInput(
        val userId: String,
        val actorName: String
)
