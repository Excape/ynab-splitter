package ch.excape.ynabsplitter.adapter.persistence.user

import org.springframework.data.redis.core.RedisHash

@RedisHash("user")
data class UserEntity(
        val id: String
) {
}