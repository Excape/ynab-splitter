package ch.excape.ynabsplitter.adapter.presentation.rest.transaction

import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.MatchedTransactionDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.transaction.document.TransactionDocument
import ch.excape.ynabsplitter.adapter.presentation.rest.user.toDocument
import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.application.outbound_ports.presentation.MatchedTransactionPresenter
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction

class RestMatchedTransactionPresenter : MatchedTransactionPresenter {

    var presentation: MatchedTransactionDocument? = null

    override fun present(matchedTransaction: MatchedTransaction?) {
        presentation = matchedTransaction?.toDocument()
    }
}

fun MatchedTransaction.toDocument(): MatchedTransactionDocument {
    return MatchedTransactionDocument(this.transactions.map { it.toDocument() })
}

fun Transaction.toDocument(): TransactionDocument {
    return TransactionDocument(id, actor.toDocument(), date.toJavaLocalDate(), amount, category?.toDocument(), memo, payee)
}
