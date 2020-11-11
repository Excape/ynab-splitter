package ch.excape.ynabsplitter.rest.security

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.invoker.ApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.context.annotation.RequestScope
import java.lang.IllegalStateException
import kotlin.reflect.full.isSubclassOf


@Configuration
class YnabConfiguration {
    @Bean
    @RequestScope
    fun ynabBudgetsApi(clientService: OAuth2AuthorizedClientService): BudgetsApi = BudgetsApi(apiClient(clientService))

    private fun apiClient(clientService: OAuth2AuthorizedClientService): ApiClient {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        if (authentication !is OAuth2AuthenticationToken) {
            throw IllegalStateException("authentication is not OAuth2 token: " + authentication::class)
        }

        val client: OAuth2AuthorizedClient = clientService.loadAuthorizedClient(
                authentication.authorizedClientRegistrationId,
                authentication.name)

        val apiClient = ApiClient()
        apiClient.getAuthentication("bearer")
        apiClient.setApiKey(client.accessToken.tokenValue)
        apiClient.setApiKeyPrefix("Bearer")
        return apiClient
    }
}