package ch.excape.ynabsplitter

import ch.excape.ynabsplitter.rest.YnabSplitterProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableConfigurationProperties(YnabSplitterProperties::class)
@EnableScheduling
class YnabSplitterApplication

fun main(args: Array<String>) {
    runApplication<YnabSplitterApplication>(*args)
}
