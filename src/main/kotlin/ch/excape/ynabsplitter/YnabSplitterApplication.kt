package ch.excape.ynabsplitter

import ch.excape.ynabsplitter.adapter.ynab.YnabClientIntegrationConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import


@SpringBootApplication
@Import(YnabClientIntegrationConfig::class)
class YnabSplitterApplication

fun main(args: Array<String>) {
	runApplication<YnabSplitterApplication>(*args)
}
