package ch.excape.ynabsplitter.rest.security

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.model.BudgetSummaryResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class UserController(private val ynabService: BudgetsApi) {
    @GetMapping("/user")
    fun user(principal: Principal) : Any {
        return principal
    }

    @GetMapping("/budgets")
    fun budgets() : BudgetSummaryResponse? {
        return ynabService.getBudgets(false)
    }
}