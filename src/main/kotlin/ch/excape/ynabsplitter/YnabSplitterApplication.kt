package ch.excape.ynabsplitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


@SpringBootApplication
class YnabSplitterApplication

fun main(args: Array<String>) {
	runApplication<YnabSplitterApplication>(*args)
}
