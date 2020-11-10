package ch.excape.ynabsplitter.domain

enum class Actor(val firstName: String, val budgetId: String, val accountId: String) {
    ROBIN("Robin","fef04036-5381-46ce-978d-375507a9e26e", "d4a3b145-e1c7-4ccf-914e-b80d16bb82f6"),
    SOPHIE("Sophie","710d9d09-a1e8-4951-84cc-a5c261947839", "9bd454eb-fd28-4472-a629-11df1c0d2272");

    override fun toString(): String {
        return firstName
    }

}