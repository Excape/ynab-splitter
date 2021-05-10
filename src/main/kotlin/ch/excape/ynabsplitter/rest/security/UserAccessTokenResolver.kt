package ch.excape.ynabsplitter.rest.security

import org.springframework.context.annotation.Profile
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Service

/**
 * Use [AuthorizedClientServiceOAuth2AuthorizedClientManager] to get the access token of a user
 * with a token for YNAB. Tries to refresh the token if expired
 */
@Service
@Profile("prod")
class UserAccessTokenResolver(
    private val clientService: OAuth2AuthorizedClientService,
    private val clientRepo: ClientRegistrationRepository
) {

    companion object {
        // must be same as registration name in spring.security.oauth2.client.registration
        val CLIENT_REGISTRATION_ID = "ynab"
    }

    fun getAccessToken(userId: String): String? {
        val clientManager = buildClientManager()

        val oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
            .withClientRegistrationId(CLIENT_REGISTRATION_ID)
            .principal(userId)
            .build()

        val reauthorizedClient = clientManager.authorize(oAuth2AuthorizeRequest)
        return reauthorizedClient?.accessToken?.tokenValue
    }

    private fun buildClientManager(): AuthorizedClientServiceOAuth2AuthorizedClientManager {
        // use the client provider for refreshing tokens, since we will already have a token,
        // but it might have expired. This client provider will refresh it
        val clientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
            .refreshToken()
            .build()

        return AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRepo, clientService).apply {
            setAuthorizedClientProvider(clientProvider)
        }
    }
}
