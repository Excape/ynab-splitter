package ch.excape.ynabsplitter.application.outbound_ports.presentation

import ch.excape.ynabsplitter.domain.User

interface UserPresenter {
    fun present(user: User)
}