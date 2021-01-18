package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_actors.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.GetActorsPresenter

interface IGetActors {
    fun executeWith(userId: String, presenter: GetActorsPresenter)
}