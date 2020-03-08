package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.UnapprovedTransactionDocument
import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.domain.toJavaLocalDate

class RestTransactionsListPresenter : TransactionListPresenter {

    var presentation: List<UnapprovedTransactionDocument>? = null

    override fun present(transactions: List<MatchedTransaction>) {
        presentation = transactions.map { it.toDocument() }
    }

}

private fun MatchedTransaction.toDocument(): UnapprovedTransactionDocument {
    val firstTransaction = this.transactions.first()
    return UnapprovedTransactionDocument(
            this.id,
            firstTransaction.date.toJavaLocalDate(),
            firstTransaction.amount,
            mapCategory(this.transactions),
            firstTransaction.memo,
            firstTransaction.payee
    )
}

fun mapCategory(transactions: MutableList<Transaction>): Map<Actor, String> =
        transactions
                .filter { it.category != null }
                .associateBy({ it.actor!! }, { it.category!! })

