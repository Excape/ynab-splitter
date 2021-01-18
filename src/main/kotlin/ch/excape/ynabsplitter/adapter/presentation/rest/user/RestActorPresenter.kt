package ch.excape.ynabsplitter.adapter.presentation.rest.user

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.ActorDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.GetActorsPresenter
import ch.excape.ynabsplitter.domain.ActorWithBudget

class RestActorsPresenter : GetActorsPresenter {

    var presentation: List<ActorDocument>? = null

    override fun present(actors: List<ActorWithBudget>) {
        presentation = actors.map { it.toDocument() }
    }

}

private fun ActorWithBudget.toDocument() =
        ActorDocument(actorName.name, budgetName, accountName)
