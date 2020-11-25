package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.SplitterActorDocument
import ch.excape.ynabsplitter.adapter.rest.document.UserDocument
import ch.excape.ynabsplitter.adapter.rest.document.UserSettingsDocument
import ch.excape.ynabsplitter.adapter.rest.document.toDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UserPresenter
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings

class RestUserPresenter : UserPresenter {
    var presentation: UserDocument? = null;
    override fun present(user: User) {
        presentation = user.toDocument()
    }
}