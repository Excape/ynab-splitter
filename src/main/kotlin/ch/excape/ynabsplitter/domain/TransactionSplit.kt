package ch.excape.ynabsplitter.domain

class TransactionSplit(private val splits: Map<String, Double>) {

    constructor(vararg splits: Pair<String, Double>) : this(hashMapOf(*splits))

    init {
        checkConsistency()
    }

    companion object {
        fun allOnOne(actor: String, allActors: List<String>) : TransactionSplit {
            val splits = allActors.associateWith {
                when (it) {
                    actor -> 1.0
                    else -> 0.0
                }
            }
            return TransactionSplit(splits)
        }
    }

    operator fun get(actor: String) = splits[actor]

    private fun checkConsistency() {
        if (!hasSumOf1()) {
            throw IllegalArgumentException("split does not sum to 1")
        }
    }

    private fun hasSumOf1(): Boolean {
        return splits.values.sum() == 1.0
    }
}