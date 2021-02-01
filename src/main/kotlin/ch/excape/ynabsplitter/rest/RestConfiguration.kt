package ch.excape.ynabsplitter.rest

import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabsplitter.adapter.notification.FakePushMessageRepository
import ch.excape.ynabsplitter.adapter.notification.WebPushMessageRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.AuditLogMongoCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.MongoAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.InMemorySubscriptionRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.mongo.MongoSubscriptionCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.mongo.MongoSubscriptionRepository
import ch.excape.ynabsplitter.adapter.persistence.user.InMemoryUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.MongoUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.UserCrudRepository
import ch.excape.ynabsplitter.adapter.ynab.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
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
    @Profile("dev")
    fun readCategoriesRepositoryDev() = InMemoryCategoriesRepository()

    @Bean
    @Primary
    @Profile("dev")
    fun readTransactionRepositoryDev() = InMemoryTransactionRepository()

    @Bean
    @Profile("dev")
    fun saveTransactionRepositoryDev() = InMemoryTransactionRepository()

    @Bean
    @Profile("dev")
    fun auditLogRepositoryDev() = InMemoryAuditLogRepository()

    @Bean
    @Profile("prod")
    fun auditLogRepositoryProd() = MongoAuditLogRepository(auditLogCrudRepository)

    @Bean
    @Profile("prod")
    fun readCategoriesRepositoryProd() = YnabCategoriesRepository(ynabCategoriesApi)

    @Bean
    @Primary
    @Profile("prod")
    fun readTransactionRepositoryProd() = YnabTransactionRepository(ynabTransactionsApi, properties.dryRun)

    @Bean
    @Profile("prod")
    fun saveTransactionRepositoryProd() = YnabTransactionRepository(ynabTransactionsApi, properties.dryRun)

    @Bean
    @Profile("dev")
    fun userRepositoryDev() = InMemoryUserRepository()

    @Bean
    @Profile("prod")
    fun userRepositoryProd() = MongoUserRepository(userCrudRepository)

    @Bean
    @Profile("prod")
    fun readBudgetsRepositoryProd() = YnabReadBudgetsRepository(ynabBudgetsApi)

    @Bean
    @Profile("dev")
    fun readBudgetsRepositoryDev() = InMemoryReadBudgetsRepository()

    @Bean
    @Profile("dev")
    fun subscriptionRepositoryDev() = InMemorySubscriptionRepository()

    @Bean
    @Profile("prod")
    fun subscriptionRepositoryProd() = MongoSubscriptionRepository(subscriptionCrudRepository)


    @Bean
    @Profile("prod")
    fun pushMessageRepositoryProd() = WebPushMessageRepository(
            properties.notifications.publicKey,
            properties.notifications.privateKey)

    @Bean
    @Profile("dev")
    fun pushMessageRepositoryDev() = FakePushMessageRepository()
}