package ch.excape.ynabsplitter.adapter.rest

import ch.excape.ynabsplitter.adapter.rest.document.ActorCategoryDocument
import ch.excape.ynabsplitter.adapter.rest.document.UnapprovedTransactionDocument
import ch.excape.ynabsplitter.adapter.rest.document.toDocument
import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction

class RestTransactionsListPresenter : TransactionListPresenter {

    var presentation: List<UnapprovedTransactionDocument>? = null

    override fun present(transactions: List<MatchedTransaction>) {
        presentation = transactions.map { it.toSingleTransactionDocument() }
    }

}

private fun MatchedTransaction.toSingleTransactionDocument(): UnapprovedTransactionDocument {
    val firstTransaction = this.transactions.first()
    return UnapprovedTransactionDocument(
            this.id,
            firstTransaction.date.toJavaLocalDate(),
            mapActors(this.transactions),
            firstTransaction.amount,
            mapCategoriesToActors(this.transactions),
            firstTransaction.memo,
            firstTransaction.payee
    )
}

fun mapActors(transactions: List<Transaction>): List<String> = transactions.map { it.actor.actorName.name }

fun mapCategoriesToActors(transactions: MutableList<Transaction>): List<ActorCategoryDocument> =
        transactions
                .filter { it.category != null }
                .map {ActorCategoryDocument(it.actor.actorName.name, it.category!!.toDocument())}

