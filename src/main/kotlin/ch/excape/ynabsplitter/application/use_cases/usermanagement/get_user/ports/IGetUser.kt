package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports

import ch.excape.ynabsplitter.application.outbound_ports.presentation.UserPresenter

/**
 * Retrieve a user, or if non-existing, create a new user with default settings
 */
interface IGetUser {
    fun executeWith(input: GetUserInput, presenter: UserPresenter)
}