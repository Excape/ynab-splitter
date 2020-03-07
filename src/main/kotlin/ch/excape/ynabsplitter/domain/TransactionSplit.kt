package ch.excape.ynabsplitter.domain

class TransactionSplit(vararg splits: Pair<Actor, Double>) {
    private val splits: Map<Actor, Double>

    init {
        this.splits = hashMapOf(*splits)
        checkConsistency()
    }

    fun containsKey(actor: Actor?): Boolean = splits.containsKey(actor)

    operator fun get(actor: Actor) = splits[actor]

    private fun checkConsistency() {
        if (!hasSumOf1()) {
            throw IllegalArgumentException("split does not sum to 1")
        }
    }

    private fun hasSumOf1(): Boolean {
        return splits.values.sum() == 1.0
    }
}