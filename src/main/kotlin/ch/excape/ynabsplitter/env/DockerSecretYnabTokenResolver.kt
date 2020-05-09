package ch.excape.ynabsplitter.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class DockerSecretYnabTokenResolver : EnvironmentPostProcessor {

    companion object {
        private const val YNAB_TOKEN_PROPERTY = "ynab.auth.token"
        private const val YNAB_TOKEN_SECRET_PROPERTY = "ynab.auth.token.file"
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        if (System.getenv(YNAB_TOKEN_SECRET_PROPERTY) == null) return

        val resource: Resource = FileSystemResource(System.getenv(YNAB_TOKEN_SECRET_PROPERTY))
        if (resource.exists()) {
            try {
                val ynabToken = StreamUtils.copyToString(resource.inputStream, Charset.defaultCharset())
                val props = Properties()
                props[YNAB_TOKEN_PROPERTY] = ynabToken
                environment.propertySources.addLast(PropertiesPropertySource("ynabAuth", props))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
}