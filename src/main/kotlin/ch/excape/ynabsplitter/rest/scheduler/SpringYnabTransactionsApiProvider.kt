package ch.excape.ynabsplitter.rest.scheduler

import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.invoker.ApiClient
import ch.excape.ynabsplitter.adapter.notification.AccessTokenNotAvailableException
import ch.excape.ynabsplitter.adapter.notification.YnabTransactionsApiProvider
import ch.excape.ynabsplitter.rest.security.UserAccessTokenResolver
import org.springframework.stereotype.Service

/**
 * Use the registered OAuth2 token from the user to build a client for YNAB
 */
@Service
class SpringYnabTransactionsApiProvider(
        private val userAccessTokenResolver: UserAccessTokenResolver
): YnabTransactionsApiProvider {
    override fun provideTransactionsApi(userId: String): TransactionsApi {
        val accessToken = userAccessTokenResolver.getAccessToken(userId)
                ?: throw AccessTokenNotAvailableException("Unable to get access token for user $userId")

        return TransactionsApi(buildApiClient(accessToken))
    }

    fun buildApiClient(accessToken: String): ApiClient {
        return ApiClient().apply {
            getAuthentication("bearer")
            setApiKey(accessToken)
            setApiKeyPrefix("Bearer")
        }
    }
}