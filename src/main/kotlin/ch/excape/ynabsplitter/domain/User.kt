package ch.excape.ynabsplitter.domain

data class User(
        val userId: String,
        val settings: UserSettings
) {
    companion object {
        fun defaultUser(userId: String): User = User(userId, UserSettings(mutableListOf()))
    }
}

data class UserSettings(
        val actors: List<SplitterActor>
)

data class SplitterActor(
        val actorName: ActorName,
        val budgetId: String,
        val accountId: String
)

data class ActorName(val name: String)

fun List<SplitterActor>.getByName(name: String): SplitterActor {
    return this.find { it.actorName.name == name } ?: throw IllegalArgumentException("$name not in list of actors")
}