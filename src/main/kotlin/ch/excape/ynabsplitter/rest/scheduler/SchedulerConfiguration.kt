package ch.excape.ynabsplitter.rest.scheduler

import ch.excape.ynabsplitter.adapter.notification.YnabTransactionsApiProvider
import ch.excape.ynabsplitter.adapter.notification.YnabTriggerImportService
import ch.excape.ynabsplitter.adapter.persistence.user.InMemoryUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.MongoUserRepository
import ch.excape.ynabsplitter.adapter.persistence.user.mongo.UserCrudRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("prod")
class SchedulerConfiguration(
        private val ynabTransactionsApiProvider: YnabTransactionsApiProvider
) {
    @Bean
    fun triggerImportService() = YnabTriggerImportService(ynabTransactionsApiProvider)
}