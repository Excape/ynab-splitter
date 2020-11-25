package ch.excape.ynabsplitter.adapter.rest.document

data class UserDocument(
    val userId: String,
    val settings: UserSettingsDocument
)

data class UserSettingsDocument(
    val actors: List<SplitterActorDocument>
)

data class SplitterActorDocument(
        val name: String
)
