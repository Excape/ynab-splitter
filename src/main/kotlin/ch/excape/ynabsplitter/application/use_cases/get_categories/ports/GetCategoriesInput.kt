package ch.excape.ynabsplitter.application.use_cases.get_categories.ports

import ch.excape.ynabsplitter.domain.Actor

data class GetCategoriesInput(
        val actor: Actor
)
