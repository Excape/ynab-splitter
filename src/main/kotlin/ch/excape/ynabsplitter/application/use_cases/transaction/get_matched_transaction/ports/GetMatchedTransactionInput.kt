package ch.excape.ynabsplitter.application.use_cases.transaction.get_matched_transaction.ports

data class GetMatchedTransactionInput(
        val userId: String,
        val concatTransactionId: String
)