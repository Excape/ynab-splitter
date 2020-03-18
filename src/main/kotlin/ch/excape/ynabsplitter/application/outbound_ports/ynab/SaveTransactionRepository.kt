package ch.excape.ynabsplitter.application.outbound_ports.ynab

import ch.excape.ynabsplitter.domain.Transaction

interface SaveTransactionRepository {
    fun saveTransaction(transaction: Transaction)
}
