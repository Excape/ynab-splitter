package ch.excape.ynabsplitter.application.use_cases.transaction.approve_transaction.ports

import ch.excape.ynabsplitter.domain.Category

class CategoryPerActor(private val categories: Map<String, Category>) {

    constructor(vararg categories: Pair<String, Category>) : this(hashMapOf(*categories))

    operator fun get(actor: String) = categories[actor]

}