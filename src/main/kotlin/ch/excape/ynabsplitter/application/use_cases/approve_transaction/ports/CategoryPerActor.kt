package ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports

import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category

class CategoryPerActor(private val categories: Map<Actor, Category>) {

    constructor(vararg categories: Pair<Actor, Category>) : this(hashMapOf(*categories))

    operator fun get(actor: Actor) = categories[actor]

}