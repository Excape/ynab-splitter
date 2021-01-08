package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SplitterActorDocument
import ch.excape.ynabsplitter.domain.SplitterActor

interface AddActorPresenter {
 fun present(result: AddActorResult)
}

data class AddActorResult(
        val success: Boolean,
        val message: String?,
        val actor: SplitterActor?
) {
 companion object {
  fun success(actor: SplitterActor) =
          AddActorResult(true, "Saved Successfully", actor)

  fun failure(message: String) =
          AddActorResult(false, message, null)
 }
}


