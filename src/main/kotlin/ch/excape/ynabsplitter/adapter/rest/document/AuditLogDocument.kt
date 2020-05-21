package ch.excape.ynabsplitter.adapter.rest.document

import ch.excape.ynabsplitter.domain.Actor

data class AuditLogDocument(
        val oldTransaction: TransactionDocument,
        val newTransaction: TransactionDocument,
        val executingActor: Actor
) {
}