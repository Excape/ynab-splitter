package ch.excape.ynabsplitter.domain

data class Category(
        val id: String,
        val name: String?,
        val group: String?,
        val balance: Long?
) {
    constructor(id: String) : this(id, null, null, null)
}
