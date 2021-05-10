package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.adapter.notification.FakePushMessageRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.notification.InMemorySubscriptionRepository
import ch.excape.ynabsplitter.adapter.persistence.user.InMemoryUserRepository
import ch.excape.ynabsplitter.adapter.ynab.InMemoryCategoriesRepository
import ch.excape.ynabsplitter.adapter.ynab.InMemoryReadBudgetsRepository
import ch.excape.ynabsplitter.adapter.ynab.InMemoryTransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class RestConfigurationDev {

    @Bean
    fun readCategoriesRepositoryDev() = InMemoryCategoriesRepository()

    @Bean
    @Primary
    fun readTransactionRepositoryDev() = InMemoryTransactionRepository()

    @Bean
    fun saveTransactionRepositoryDev() = InMemoryTransactionRepository()

    @Bean
    fun auditLogRepositoryDev() = InMemoryAuditLogRepository()

    @Bean
    fun userRepositoryDev() = InMemoryUserRepository()

    @Bean
    fun readBudgetsRepositoryDev() = InMemoryReadBudgetsRepository()

    @Bean
    fun subscriptionRepositoryDev() = InMemorySubscriptionRepository()

    @Bean
    fun pushMessageRepositoryDev() = FakePushMessageRepository()
}
