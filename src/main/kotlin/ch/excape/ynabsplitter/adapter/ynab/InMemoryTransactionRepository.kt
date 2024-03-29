package ch.excape.ynabsplitter.adapter.ynab

import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.outbound_ports.ynab.SaveTransactionRepository
import ch.excape.ynabsplitter.domain.ActorName
import ch.excape.ynabsplitter.domain.Category
import ch.excape.ynabsplitter.domain.SplitterActor
import ch.excape.ynabsplitter.domain.Transaction
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.threeten.bp.LocalDate
import java.lang.Thread.sleep

class InMemoryTransactionRepository : ReadTransactionsRepository, SaveTransactionRepository {
    companion object {
        val log: Logger = LogManager.getLogger()
    }

    private val transactions: MutableMap<String, Transaction> = createFakeTransactions()

    override fun getTransaction(actor: SplitterActor, id: String): Transaction? {
        return transactions[id]
    }

    override fun triggerTransactionImport(actor: SplitterActor): Int {
        log.info("Transactions for ${actor.actorName} would be imported here")
        return 0;
    }

    override fun getUnapprovedTransactionsFromLastMonth(actor: SplitterActor): List<Transaction> =
        transactions.values
            .filter { it.actor.actorName == actor.actorName }
            .filter { !it.isApproved }

    override fun saveTransaction(transaction: Transaction) {
        // fake delay
        sleep(500)
    }

    private fun createFakeTransactions(): MutableMap<String, Transaction> {
        val aliceActor = SplitterActor(ActorName("Sophie"), "fake-budget", "fake-account")
        val bobActor = SplitterActor(ActorName("Robin"), "fake-budget", "fake-account")
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
