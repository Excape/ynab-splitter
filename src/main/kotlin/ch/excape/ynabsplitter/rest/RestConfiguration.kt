package ch.excape.ynabsplitter.rest

import ch.excape.ynabclient.api.AccountsApi
import ch.excape.ynabclient.api.BudgetsApi
import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabsplitter.adapter.persistence.auditlog.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.AuditLogMongoCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo.MongoAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.user.InMemoryUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.MongoUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.UserCrudRepository
import ch.excape.ynabsplitter.adapter.ynab.*
import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadBudgetsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import org.springframework.beans.factory.annotation.Value
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
        @Value("\${ynabsplitter.dry-run}")
        private val dryRun: Boolean
) {

    @Bean
    @Profile("dev")
    fun readCategoriesRepositoryDev(): ReadCategoriesRepository = InMemoryCategoriesRepository()

    @Bean
    @Primary
    @Profile("dev")
    fun readTransactionRepositoryDev(): ReadTransactionsRepository = InMemoryTransactionRepository()

    @Bean
    @Profile("dev")
    fun saveTransactionRepositoryDev(): SaveTransactionRepository = InMemoryTransactionRepository()

    @Bean
    @Profile("dev")
    fun auditLogRepositoryDev() : AuditLogRepository = InMemoryAuditLogRepository()

    @Bean
    @Profile("prod", "dev")
    fun auditLogRepositoryProd() : AuditLogRepository = MongoAuditLogRepository(auditLogCrudRepository)

    @Bean
    @Profile("prod")
    fun readCategoriesRepositoryProd(): ReadCategoriesRepository = YnabCategoriesRepository(ynabCategoriesApi)

    @Bean
    @Primary
    @Profile("prod")
    fun readTransactionRepositoryProd(): ReadTransactionsRepository = YnabTransactionRepository(ynabTransactionsApi, dryRun)

    @Bean
    @Profile("prod")
    fun saveTransactionRepositoryProd(): SaveTransactionRepository = YnabTransactionRepository(ynabTransactionsApi, dryRun)

    @Bean
    @Profile("dev")
    fun userRepositoryDev(): UserRepository = InMemoryUserRepository()

    @Bean
    @Profile("prod", "dev")
    fun userRepositoryProd(): UserRepository = MongoUserRepository(userCrudRepository)

    @Bean
    @Profile("prod")
    fun readBudgetsRepositoryProd(): ReadBudgetsRepository = YnabReadBudgetsRepository(ynabBudgetsApi)

    @Bean
    @Profile("dev")
    fun readBudgetsRepositoryDev(): ReadBudgetsRepository = InMemoryReadBudgetsRepository()
}