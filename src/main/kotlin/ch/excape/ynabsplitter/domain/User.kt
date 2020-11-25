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
        val name: String,
        val budgetId: String,
        val accountId: String
)
fun List<SplitterActor>.getByName(name: String): SplitterActor {
    return this.find { it.name == name } ?: throw IllegalArgumentException("$name not in list of actors")
}