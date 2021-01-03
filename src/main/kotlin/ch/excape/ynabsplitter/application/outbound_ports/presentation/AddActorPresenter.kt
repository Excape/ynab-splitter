package ch.excape.ynabsplitter.application.outbound_ports.presentation

interface AddActorPresenter {
 fun present(result: AddActorResult)
}

data class AddActorResult(
        val success: Boolean,
        val message: String?
) {
 companion object {
  fun success(): AddActorResult {
   return AddActorResult(true, "Saved Successfully")
  }
 }
}


