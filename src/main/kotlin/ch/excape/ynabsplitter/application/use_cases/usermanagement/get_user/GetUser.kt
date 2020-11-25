package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UserPresenter
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.GetUserInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.IGetUser
import ch.excape.ynabsplitter.domain.User

class GetUser(private val userRepository: UserRepository) : IGetUser {
    override fun executeWith(input: GetUserInput, presenter: UserPresenter) {
        val user = getOrCreateUser(input.userId)
        presenter.present(user)
    }

    private fun getOrCreateUser(userId: String): User =
            userRepository.getUser(userId) ?: createNewUser(userId)

    private fun createNewUser(userId: String) : User{
        val newUser = User.defaultUser(userId)
        userRepository.createUser(newUser)
        return newUser;
    }

}