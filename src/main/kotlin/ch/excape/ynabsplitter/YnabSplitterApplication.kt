package ch.excape.ynabsplitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
//@Import(YnabClientIntegrationConfig::class)
class YnabSplitterApplication

fun main(args: Array<String>) {
	runApplication<YnabSplitterApplication>(*args)
}
