package ch.excape.ynabsplitter.domain

enum class Actor(val firstName: String, val budgetId: String, val accountId: String) {
    ROBIN("Robin","fef04036-5381-46ce-978d-375507a9e26e", "d4a3b145-e1c7-4ccf-914e-b80d16bb82f6"),
    SOPHIE("Sophie","12586d8c-2d38-4abd-be08-fb807a84f359", "13e8c014-3c9e-4d59-acb6-fb90c59a0296");

    override fun toString(): String {
        return firstName
    }

//    @JsonValue
//    fun toJsonValue() : String {
//        return firstName
//    }

    // TODO this only works if it's deserializing a type in the body of a request, not as a path variable
//    @JsonValue
//    fun toJsonValue() : String {
//        return firstName
//    }

//    companion object {
//        @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
//        @JvmStatic
//        fun fromString(key: String?) : Actor? {
//            return values().firstOrNull {it.firstName == key}
//        }
//    }

}