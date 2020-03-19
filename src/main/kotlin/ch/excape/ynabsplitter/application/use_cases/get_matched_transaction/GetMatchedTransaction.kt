package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction

import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.IGetMatchedTransaction
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction

class GetMatchedTransaction(private val transactionRepository: ReadTransactionsRepository): IGetMatchedTransaction {

    override fun executeWith(input: GetMatchedTransactionInput, presenter: MatchedTransactionPresenter) {
        val transactionIds = untangleTransactionId(input.concatTransactionId)

        val transactions = transactionIds.map(this::retrieveTransaction)

        val matchedTransaction = MatchedTransaction(transactions.toMutableList())

        presenter.present(matchedTransaction)
    }

    private fun retrieveTransaction(transactionId: String): Transaction {
        // We don't know the actor, so we're gonna try them all
        val transaction = Actor.values().asSequence() // asSequence causes lazy evaluation :)
                .mapNotNull { transactionRepository.getTransaction(it, transactionId) }
                .firstOrNull()
        return transaction ?: throw IllegalArgumentException("Transaction ${transactionId} not found")
    }

    private fun untangleTransactionId(concatTransactionId: String): List<String> =
            concatTransactionId.split("_")
}