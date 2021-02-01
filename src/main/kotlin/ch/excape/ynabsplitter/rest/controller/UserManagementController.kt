package ch.excape.ynabsplitter.rest.controller

import ch.excape.ynabsplitter.adapter.presentation.rest.user.*
import ch.excape.ynabsplitter.adapter.presentation.rest.user.document.*
import ch.excape.ynabsplitter.application.outbound_ports.notification.PushMessageRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.SubscriptionRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.SubscribePush
import ch.excape.ynabsplitter.application.use_cases.notifications.subscribe_push.ports.SubscribePushInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.AddActor
import ch.excape.ynabsplitter.application.use_cases.usermanagement.add_actor.ports.AddActorInput
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_actors.GetActors
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_budgets.GetBudgets
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.GetUser
import ch.excape.ynabsplitter.application.use_cases.usermanagement.get_user.ports.GetUserInput
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/v1/user")
class UserManagementController(
        private val userRepository: UserRepository,
        private val budgetsRepository: ReadBudgetsRepository,
        private val subscriptionRepository: SubscriptionRepository,
        private val pushMessageRepository: PushMessageRepository
) {

    @GetMapping
    fun getUser(principal: Principal): UserDocument {
        return getLoggedInUser(principal)
    }

    @GetMapping("/actors")
    fun getActors(principal: Principal): List<ActorDocument> {
        val user = getLoggedInUser(principal)
        val presenter = RestActorsPresenter()
        val getActors = GetActors(userRepository, budgetsRepository)

        getActors.executeWith(user.userId, presenter)

        return presenter.presentation!!
    }

    @GetMapping("/budgets")
    fun getBudgets(principal: Principal): List<BudgetDocument> {
        val user = getLoggedInUser(principal)
        val presenter = RestBudgetsPresenter()
        GetBudgets(userRepository, budgetsRepository).executeWith(user.userId, presenter)
        return presenter.presentation!!
    }

    @PostMapping("/addActor")
    fun addActor(@RequestBody request: AddActorRequest, principal: Principal): AddActorResultDocument {
        val user = getLoggedInUser(principal)
        val input = AddActorInput(user.userId, request.actorName, request.budgetId, request.accountId)
        val presenter = RestAddActorPresenter()
        AddActor(userRepository).executeWith(input, presenter)

        return presenter.presentation!!
    }

    @PostMapping("/subscribePush")
    fun subscribePush(@RequestBody request: SubscribePushRequest, principal: Principal) {
        val user = getLoggedInUser(principal)
        val input = SubscribePushInput(request.toSubscription(user.userId))
        val subscribePush = SubscribePush(subscriptionRepository, pushMessageRepository)
        subscribePush.executeWith(input)
    }

    private fun getLoggedInUser(principal: Principal): UserDocument {
        val input = GetUserInput(userId = principal.name)
        val presenter = RestUserPresenter()
        GetUser(userRepository).executeWith(input, presenter)
        return presenter.presentation!!
    }
}