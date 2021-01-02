package ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorPresenter

interface IAddActor {
    fun executeWith(input: AddActorInput, presenter: AddActorPresenter)
}