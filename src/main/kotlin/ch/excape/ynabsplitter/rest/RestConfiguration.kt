package ch.excape.ynabsplitter.rest

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabsplitter.adapter.notification.WebPushMessageRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.AuditLogMongoCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.MongoAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.mongo.MongoSubscriptionCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.mongo.MongoSubscriptionRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.MongoUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.UserCrudRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabCategoriesRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabReadBudgetsRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabTransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("prod")
class RestConfiguration(
    private val ynabTransactionsApi: TransactionsApi,
    private val ynabCategoriesApi: CategoriesApi,
    private val ynabBudgetsApi: BudgetsApi,
    private val auditLogCrudRepository: AuditLogMongoCrudRepository,
    private val userCrudRepository: UserCrudRepository,
    private val subscriptionCrudRepository: MongoSubscriptionCrudRepository,
    private val properties: YnabSplitterProperties
) {

    @Bean
    fun auditLogRepositoryProd() = MongoAuditLogRepository(auditLogCrudRepository)

    @Bean
    fun readCategoriesRepositoryProd() = YnabCategoriesRepository(ynabCategoriesApi)

    @Bean
    @Primary
    fun readTransactionRepositoryProd() = YnabTransactionRepository(ynabTransactionsApi, properties.dryRun)

    @Bean
    fun saveTransactionRepositoryProd() = YnabTransactionRepository(ynabTransactionsApi, properties.dryRun)

    @Bean
    fun userRepositoryProd() = MongoUserRepository(userCrudRepository)

    @Bean
    fun readBudgetsRepositoryProd() = YnabReadBudgetsRepository(ynabBudgetsApi)

    @Bean
    fun subscriptionRepositoryProd() = MongoSubscriptionRepository(subscriptionCrudRepository)

    @Bean
    fun pushMessageRepositoryProd() = WebPushMessageRepository(
        properties.notifications.publicKey,
        properties.notifications.privateKey
    )
}
