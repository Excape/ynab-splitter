package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import org.threeten.bp.LocalDate

class InMemoryTransactionRepository : ReadTransactionsRepository, SaveTransactionRepository {

    private val transactions: MutableMap<String, Transaction> = createFakeTransactions()

    override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
        return transactions[id]
    }

    override fun triggerTransactionImport(actor: SplitterActor) {
        println("Transactions for ${actor.name} would be imported here")
    }

    override fun getAllTransactionsFromLastWeek(actor: SplitterActor): List<Transaction> =
            transactions.values.filter { it.actor.name == actor.name }

    override fun getAllTransactionsBetween(actor: SplitterActor, startDate: LocalDate, endDate: LocalDate): List<Transaction> =
            transactions.values.filter { it.date in startDate..endDate }

    override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> =
            getAllTransactionsFromLastWeek(actor).filter { !it.isApproved }

    override fun saveTransaction(transaction: Transaction) {
        transactions[transaction.id] = transaction
    }

    private fun createFakeTransactions(): MutableMap<String, Transaction> {
        val aliceActor = SplitterActor("Anusha", "fake-budget", "fake-account")
        val bobActor = SplitterActor("Bartholomew", "fake-budget", "fake-account")
        val transactionsAlice = listOf(
                Transaction(
                        "t0",
                        LocalDate.ofYearDay(2019, 1),
                        -10000,
                        Category("catGroceries", "Groceries", "True Expenses", 2300),
                        null,
                        false,
                        "Migros",
                        aliceActor
                ),
                Transaction(
                        "t1",
                        LocalDate.ofYearDay(2019, 2),
                        -1233,
                        Category("catCoffee", "Coffee", "True Expenses", 2300),
                        "oops",
                        false,
                        "Starbucks",
                        aliceActor
                ),
                Transaction(
                        "t2",
                        LocalDate.ofYearDay(2019, 3),
                        -52055,
                        null,
                        null,
                        false,
                        "Delta Airways",
                        aliceActor
                ),
                Transaction(
                        "t3",
                        LocalDate.ofYearDay(2019, 4),
                        134000,
                        null,
                        null,
                        false,
                        "Clothes refund",
                        aliceActor
                )
        )
        val transactionsBob = transactionsAlice.withIndex()
                .map { t -> t.value.copy(id = "t" + (t.index + transactionsAlice.size), actor = bobActor) }

        return sequenceOf(transactionsAlice, transactionsBob).flatten()
                .map { it.id to it }
                .toMap().toMutableMap()

    }
}
