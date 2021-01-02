package ch.excape.ynabsplitter.rest.controller

import ch.excape.ynabsplitter.adapter.rest.RestAddActorPresenter
import ch.excape.ynabsplitter.adapter.rest.RestUserPresenter
import ch.excape.ynabsplitter.adapter.rest.document.AddActorRequest
import ch.excape.ynabsplitter.adapter.rest.document.UserDocument
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AddActorResult
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.AddActor
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports.AddActorInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.GetUser
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.GetUserInput
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/user")
class UserManagementController(private val userRepository: UserRepository) {

    @PostMapping("/addActor")
    fun addActor(@RequestBody request: AddActorRequest, principal: Principal): AddActorResult {
        val user = getLoggedInUser(principal)
        val input = AddActorInput(user.userId, request.actorName, request.budgetId, request.accountId)
        val presenter = RestAddActorPresenter()
        AddActor(userRepository).executeWith(input, presenter)

        return presenter.presentation!!
    }

    private fun getLoggedInUser(principal: Principal): UserDocument {
        val input = GetUserInput(userId = principal.name)
        val presenter = RestUserPresenter()
        GetUser(userRepository).executeWith(input, presenter)
        return presenter.presentation!!
    }
}