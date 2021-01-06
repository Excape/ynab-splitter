package ch.excape.ynabsplitter.adapter.presentation.rest.user.document

data class UserDocument(
    val userId: String,
    val settings: UserSettingsDocument
)

data class UserSettingsDocument(
    val actors: List<SplitterActorDocument>
)

data class SplitterActorDocument(
        val name: String,
        val budgetId: String,
        val accountId: String
)
