package ch.excape.ynabsplitter.adapter.persistence

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfiguration {

    @Value("\${redis.hostname:localhost}")
    private lateinit var redisHost: String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration(redisHost)
        return JedisConnectionFactory(redisConfig)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }
}