package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.adapter.ynab.toJavaLocalDate
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.MatchedTransaction
import ch.excape.ynabsplitter.domain.Transaction

fun Category.toDocument(): CategoryDocument = CategoryDocument(id, name ?: "", group, balance)

fun MatchedTransaction.toDocument(): MatchedTransactionDocument {
    return MatchedTransactionDocument(this.transactions.map { it.toDocument() })
}

fun Transaction.toDocument(): TransactionDocument {
    return TransactionDocument(id, actor, date.toJavaLocalDate(), amount, category?.toDocument(), memo, payee)
}