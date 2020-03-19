package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports

data class GetMatchedTransactionInput(
        val concatTransactionId: String
)