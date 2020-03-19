package ch.excape.ynabsplitter.ynab_integration

import ch.excape.ynabclient.api.AccountsApi
import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabclient.invoker.ApiClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class YnabConfiguration {

    @Value("\${ynab.auth.token}")
    lateinit var AUTH_TOKEN: String

    @Bean
    fun ynabAccountsApi() : AccountsApi = AccountsApi(apiClient())

    @Bean
    fun ynabBudgetsApi() : BudgetsApi = BudgetsApi(apiClient())

    @Bean
    fun ynabTransactionsApi() : TransactionsApi = TransactionsApi(apiClient())

    @Bean
    fun ynabCategoriesApi() : CategoriesApi = CategoriesApi(apiClient())

    @Bean
    fun apiClient(): ApiClient {
        val apiClient = ApiClient()
        apiClient.getAuthentication("bearer")
        apiClient.setApiKey(AUTH_TOKEN)
        apiClient.setApiKeyPrefix("Bearer")
        return apiClient
    }
}
