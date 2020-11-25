package ch.excape.ynabsplitter.rest

import ch.excape.ynabclient.api.CategoriesApi
import ch.excape.ynabclient.api.TransactionsApi
import ch.excape.ynabsplitter.adapter.persistence.auditlog.AuditLogCrudRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.auditlog.RedisAuditLogRepository
import ch.excape.ynabsplitter.adapter.persistence.user.InMemoryUserRepository
import ch.excape.ynabsplitter.adapter.ynab.InMemoryCategoriesRepository
import ch.excape.ynabsplitter.adapter.ynab.InMemoryTransactionRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabCategoriesRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabTransactionRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadCategoriesRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
class RestConfiguration(
       private val ynabTransactionsApi: TransactionsApi,
       private val ynabCategoriesApi: CategoriesApi,
       private val auditLogCrudRepository: AuditLogCrudRepository
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
    fun userRepositoryDev(): UserRepository = InMemoryUserRepository()

    @Bean
    @Profile("dev", "prod")
    fun auditLogRepositoryDev() : AuditLogRepository = InMemoryAuditLogRepository()

    // TODO enable redis
//    @Bean
//    @Profile("prod")
//    fun auditLogRepositoryProd() : AuditLogRepository = RedisAuditLogRepository(auditLogCrudRepository)

    @Bean
    @Profile("prod")
    fun readCategoriesRepositoryProd(): ReadCategoriesRepository = YnabCategoriesRepository(ynabCategoriesApi)

    @Bean
    @Primary
    @Profile("prod")
    fun readTransactionRepositoryProd(): ReadTransactionsRepository = YnabTransactionRepository(ynabTransactionsApi)

    @Bean
    @Profile("prod")
    fun saveTransactionRepositoryProd(): SaveTransactionRepository = YnabTransactionRepository(ynabTransactionsApi)

    @Bean
    @Profile("prod")
    // TODO implement real thing
    fun userRepositoryProd(): UserRepository = InMemoryUserRepository()
}