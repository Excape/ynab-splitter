package ch.excape.ynabsplitter.adapter.persistence.user

import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.User
import ch.excape.ynabsplitter.domain.UserSettings
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
        @Id
        val userId: String,
        val settings: UserSettingsEntity,
) {
    fun toDomain() = User(userId, settings.toDomain())
}

data class UserSettingsEntity(
        val actors: List<ActorEntity>
) {
    fun toDomain() = UserSettings(actors.map { it.toDomain() })
}

data class ActorEntity(
        var name: String,
        var budgetId: String,
        var accountId: String,
) {
    fun toDomain() = SplitterActor(ActorName(name), budgetId, accountId)
}


fun User.toEntity(): UserEntity = UserEntity(userId, settings.toEntity())

private fun UserSettings.toEntity(): UserSettingsEntity =
        UserSettingsEntity(actors.map { it.toEntity() })

private fun SplitterActor.toEntity(): ActorEntity =
        ActorEntity(actorName.name, budgetId, accountId)