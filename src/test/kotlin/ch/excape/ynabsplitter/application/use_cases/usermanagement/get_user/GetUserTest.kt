package ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.UserPresenter
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.GetUserInput
import ch.excape.ynabsplitter.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GetUserTest {
    @Test
    internal fun getUser_existingUser_isCreated() {
        val userRepository = object : UserRepository {
            override fun getUser(userId: String): User? = null
            override fun createUser(user: User) {}
        }

        val useCase = GetUser(userRepository)
        val input = GetUserInput("myUserId")
        useCase.executeWith(input, TestUserPresenter());

        val result = TestUserPresenter().presentation;
        assertThat(result)
                .isNotNull
                .extracting { it!!.userId }
                .isEqualTo("myUserId")

    }

    inner class TestUserPresenter : UserPresenter {
        var presentation: User? = null;
        override fun present(user: User) {
            presentation = user;
        }
    }
}