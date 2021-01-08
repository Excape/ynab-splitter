package ch.excape.ynabsplitter.rest.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils
import java.io.IOException
import java.util.*

class DockerSecretMongoPasswordResolver : EnvironmentPostProcessor {

    companion object {
        private const val MONGO_PASSWORD_PROPERTY = "spring.data.mongodb.password"
        private const val MONGO_PASSWORD_SECRET_FILE_PROPERTY = "mongo.password.secret.file"
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        if (System.getenv(MONGO_PASSWORD_SECRET_FILE_PROPERTY) == null) return

        val resource: Resource = FileSystemResource(System.getenv(MONGO_PASSWORD_SECRET_FILE_PROPERTY))
        if (resource.exists()) {
            try {
                val ynabToken = StreamUtils.copyToString(resource.inputStream, Charsets.UTF_8)
                val props = Properties()
                props[MONGO_PASSWORD_PROPERTY] = ynabToken
                environment.propertySources.addLast(PropertiesPropertySource("mongo", props))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
}