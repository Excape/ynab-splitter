package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabclient.api.AccountsApi
import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.invoker.ApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class YnabClientIntegrationConfig {
    val AUTH_TOKEN = "0bd5cf5d6baf6c6f4f0f846e767ed78b03e7abc6ca0c17f169535c79c57f7f32"

    @Bean
    fun ynabAccountsApi() : AccountsApi = AccountsApi(apiClient())

    @Bean
    fun ynabBudgetsApi() : BudgetsApi = BudgetsApi(apiClient())

    @Bean
    fun ynabTransactionsApi() : TransactionsApi = TransactionsApi(apiClient())

    @Bean
    fun apiClient(): ApiClient {
        val apiClient = ApiClient()
        apiClient.getAuthentication("bearer")
        apiClient.setApiKey(AUTH_TOKEN)
        apiClient.setApiKeyPrefix("Bearer")
        return apiClient
    }
}
