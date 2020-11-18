package ch.excape.ynabsplitter.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils
import java.io.IOException
import java.util.*

class DockerSecretYnabSecretResolver : EnvironmentPostProcessor {

    companion object {
        private const val YNAB_CLIENT_SECRET_PROPERTY = "ynab.auth.client.secret"
        private const val DOCKER_SECRET_FILE_PROPERTY = "ynab.auth.client.secret.file"
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        if (System.getenv(DOCKER_SECRET_FILE_PROPERTY) == null) return

        val resource: Resource = FileSystemResource(System.getenv(DOCKER_SECRET_FILE_PROPERTY))
        if (resource.exists()) {
            try {
                val ynabSecret = StreamUtils.copyToString(resource.inputStream, Charsets.UTF_8)
                val props = Properties()
                props[YNAB_CLIENT_SECRET_PROPERTY] = ynabSecret
                environment.propertySources.addLast(PropertiesPropertySource("ynabAuth", props))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
}