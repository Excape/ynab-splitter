package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.Transaction
import org.threeten.bp.LocalDate

class InMemoryTransactionRepository : ReadTransactionsRepository, SaveTransactionRepository {
    override fun getTransaction(actor: Actor, id: String): Transaction? {
        return transactions[id]
    }

    private val transactions: MutableMap<String, Transaction> = createFakeTransactions()

    override fun getAllTransactionsFromLastWeek(actor: Actor): List<Transaction> =
            transactions.values.filter { it.actor == actor }

    override fun getAllTransactionsBetween(actor: Actor, startDate: LocalDate, endDate: LocalDate): List<Transaction> =
            transactions.values.filter { it.date in startDate..endDate }

    override fun getUnapprovedTransactionsFromLastWeek(actor: Actor): List<Transaction> =
            getAllTransactionsFromLastWeek(actor).filter { !it.isApproved }

    override fun saveTransaction(transaction: Transaction) {
        transactions[transaction.id] = transaction
    }

    private fun createFakeTransactions(): MutableMap<String, Transaction> {
        val transactionsRobin = listOf(
                Transaction(
                        "t0",
                        LocalDate.ofYearDay(2019, 1),
                        10000,
                        Category("catGroceries", "Groceries", "True Expenses", 2300),
                        null,
                        false,
                        "Migros",
                        Actor.ROBIN
                ),
                Transaction(
                        "t1",
                        LocalDate.ofYearDay(2019, 2),
                        1233,
                        Category("catCoffee", "Coffee", "True Expenses", 2300),
                        "oops",
                        false,
                        "Starbucks",
                        Actor.ROBIN
                ),
                Transaction(
                        "t2",
                        LocalDate.ofYearDay(2019, 3),
                        52055,
                        null,
                        null,
                        false,
                        "Delta Airways",
                        Actor.ROBIN
                )
        )
        val transactionsSophie = transactionsRobin.withIndex()
                .map { t -> t.value.copy(id = "t" + (t.index + transactionsRobin.size), actor = Actor.SOPHIE) }

        return sequenceOf(transactionsRobin, transactionsSophie).flatten()
                .map { it.id to it }
                .toMap().toMutableMap()

    }
}
