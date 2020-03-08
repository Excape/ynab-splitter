package ch.excape.ynabsplitter.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class Actor(val firstName: String, val budgetId: String, val accountId: String) {
    ROBIN("Robin","fef04036-5381-46ce-978d-375507a9e26e", "d4a3b145-e1c7-4ccf-914e-b80d16bb82f6"),
    SOPHIE("Sophie","12586d8c-2d38-4abd-be08-fb807a84f359", "13e8c014-3c9e-4d59-acb6-fb90c59a0296");

    override fun toString(): String {
        return firstName
    }

    @JsonCreator
    fun fromString(key: String?) : Actor? {
        return if (key == null) null else Actor.valueOf(key)
    }

    @JsonValue
    fun toJsonValue() : String {
        return firstName
    }

}