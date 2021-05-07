package ch.excape.ynabsplitter.rest.security

import ch.excape.ynabclient.api.AccountsApi
import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.invoker.ApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.context.annotation.RequestScope


@Configuration
class YnabServicesConfiguration(
        private val userAccessTokenResolver: UserAccessTokenResolver
) {
    @Bean
    @RequestScope
    fun ynabAccountsApi(clientService: OAuth2AuthorizedClientService): AccountsApi =
            AccountsApi(apiClient())

    @Bean
    @RequestScope
    fun ynabBudgetsApi(clientService: OAuth2AuthorizedClientService): BudgetsApi =
            BudgetsApi(apiClient())

    @Bean
    @RequestScope
    fun ynabTransactionsApi(clientService: OAuth2AuthorizedClientService): TransactionsApi =
            TransactionsApi(apiClient())

    @Bean
    @RequestScope
    fun ynabCategoriesApi(clientService: OAuth2AuthorizedClientService): CategoriesApi =
            CategoriesApi(apiClient())


    private fun apiClient(): ApiClient {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication

        if (authentication !is OAuth2AuthenticationToken) {
            throw IllegalStateException("authentication is not OAuth2 token: " + authentication::class)
        }

        val accessToken = userAccessTokenResolver.getAccessToken(authentication.name)
                ?: throw IllegalStateException("Access token not available")

        return ApiClient().apply {
            getAuthentication("bearer")
            setApiKey(accessToken)
            setApiKeyPrefix("Bearer")
        }
    }
}