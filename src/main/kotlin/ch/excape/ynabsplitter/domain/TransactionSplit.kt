package ch.excape.ynabsplitter.domain

class TransactionSplit(private val splits: Map<Actor, Double>) {

    constructor(vararg splits: Pair<Actor, Double>) : this(hashMapOf(*splits))

    init {
        checkConsistency()
    }

    companion object {
        fun allOnOne(actor: Actor) : TransactionSplit {
            val allZero = Actor.values()
                    .associate { it to 0.0 }
                    .toMutableMap()
            allZero[actor] = 1.0
            val splits = allZero.toMap()
            return TransactionSplit(splits)

        }
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