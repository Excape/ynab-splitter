package ch.excape.ynabsplitter.application.use_cases.get_matched_transaction

import ch.excape.ynabsplitter.application.outbound_ports.persistence.UserRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.GetMatchedTransactionInput
import ch.excape.ynabsplitter.application.use_cases.get_matched_transaction.ports.IGetMatchedTransaction
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.User

class GetMatchedTransaction(
        private val transactionRepository: ReadTransactionsRepository,
        private val userRepository: UserRepository
        ): IGetMatchedTransaction {

    override fun executeWith(input: GetMatchedTransactionInput, presenter: MatchedTransactionPresenter) {
        val user = getUser(input.userId)

        val transactionIds = untangleTransactionId(input.concatTransactionId)

        val transactions = transactionIds.map { this.retrieveTransaction(it, user) }

        val matchedTransaction = MatchedTransaction(transactions.toMutableList())

        presenter.present(matchedTransaction)
    }

    private fun getUser(userId: String): User {
        return userRepository.getUser(userId) ?: throw IllegalArgumentException("User with id $userId not found")
    }

    private fun retrieveTransaction(transactionId: String, user: User): Transaction {
        // TODO We don't know the actor, so we're gonna try them all
        val transaction = getActors(user).asSequence() // asSequence causes lazy evaluation :)
                .mapNotNull { transactionRepository.getTransaction(it, transactionId) }
                .firstOrNull()
        return transaction ?: throw IllegalArgumentException("Transaction $transactionId not found")
    }

    private fun getActors(user: User) = user.settings.actors

    private fun untangleTransactionId(concatTransactionId: String): List<String> =
            concatTransactionId.split("_")
}