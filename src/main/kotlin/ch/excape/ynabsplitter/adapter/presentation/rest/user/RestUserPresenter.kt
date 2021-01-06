package ch.excape.ynabsplitter.adapter.presentation.rest.user

import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.SplitterActorDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.UserDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.UserSettingsDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UserPresenter
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings

class RestUserPresenter : UserPresenter {
    var presentation: UserDocument? = null
    override fun present(user: User) {
        presentation = user.toDocument()
    }
}

fun User.toDocument(): UserDocument = UserDocument(userId, settings.toDocument())

fun UserSettings.toDocument(): UserSettingsDocument = UserSettingsDocument(actors.map { it.toDocument() })

fun SplitterActor.toDocument(): SplitterActorDocument = SplitterActorDocument(actorName.name, budgetId, accountId)
