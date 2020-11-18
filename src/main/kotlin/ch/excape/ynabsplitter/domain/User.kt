package ch.excape.ynabsplitter.domain

data class User(
        val userId: String,
        val settings: UserSettings
)

data class UserSettings(
        val actors: List<SplitterActor>
)

data class SplitterActor(
        val name: String,
        val budgetId: String,
        val accountId: String
)