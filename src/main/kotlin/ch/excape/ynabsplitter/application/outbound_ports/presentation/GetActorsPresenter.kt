package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.ActorWithBudget

interface GetActorsPresenter {
    fun present(actors: List<ActorWithBudget>)
}
