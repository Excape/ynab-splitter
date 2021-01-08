package ch.excape.ynabsplitter.adapter.presentation.rest.user

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SplitterActorDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorResult
import ch.excape.ynabsplitter.domain.SplitterActor

class RestAddActorPresenter : AddActorPresenter {
    var presentation: AddActorResultDocument? = null

    override fun present(result: AddActorResult) {
        presentation = result.toDocument()
    }

    fun AddActorResult.toDocument() =
            AddActorResultDocument(success, message, actor?.toDocument())
}

data class AddActorResultDocument(
        val success: Boolean,
        val message: String?,
        val actor: SplitterActorDocument?
)
