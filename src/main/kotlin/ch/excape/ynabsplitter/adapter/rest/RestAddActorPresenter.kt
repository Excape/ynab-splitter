package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorResult

class RestAddActorPresenter : AddActorPresenter {
    var presentation: AddActorResult? = null

    override fun present(result: AddActorResult) {
        presentation = result
    }

}
