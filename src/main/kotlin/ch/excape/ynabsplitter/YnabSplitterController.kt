package ch.excape.ynabsplitter

import ch.excape.ynabsplitter.adapter.persistence.InMemoryAuditLogRepository
import ch.excape.ynabsplitter.adapter.rest.RestCategoryListPresenter
import ch.excape.ynabsplitter.adapter.rest.RestTransactionPresenter
import ch.excape.ynabsplitter.adapter.rest.RestTransactionsListPresenter
import ch.excape.ynabsplitter.adapter.rest.document.UnapprovedTransactionDocument
import ch.excape.ynabsplitter.adapter.ynab.InMemoryTransactionRepository
import ch.excape.ynabsplitter.adapter.ynab.YnabCategoriesRepository
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.approve_transaction.ports.IApproveTransaction
import ch.excape.ynabsplitter.application.use_cases.get_categories.GetCategories
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.GetCategoriesInput
import ch.excape.ynabsplitter.application.use_cases.get_categories.ports.IGetCategories
import ch.excape.ynabsplitter.application.use_cases.get_transaction.GetTransaction
import ch.excape.ynabsplitter.application.use_cases.get_transaction.ports.IGetTransaction
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListUnapprovedTransactions
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput
import ch.excape.ynabsplitter.domain.Actor
import ch.excape.ynabsplitter.domain.Category
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import java.nio.charset.Charset

@RestController
@RequestMapping("/api/v1")
class YnabSplitterController(
        private val readCategoriesRepository: YnabCategoriesRepository
) {

    private val transactionRepository = InMemoryTransactionRepository()

    @GetMapping("/transactions")
    fun getTransactions(): List<UnapprovedTransactionDocument> {
        val listUnapprovedTransactions: IListUnapprovedTransactions = ListUnapprovedTransactions(transactionRepository)
        val transactionPresenter = RestTransactionsListPresenter()

        val input = ListUnapprovedTransactionsInput(listOf(Actor.ROBIN, Actor.SOPHIE))

        listUnapprovedTransactions.executeWith(input, transactionPresenter)

        return transactionPresenter.presentation!!
    }

    @GetMapping("/categories/{actor}")
    fun getCategories(@PathVariable("actor") actor: Actor): List<Category> {
        // TODO Create Document for Category
        val getCategories : IGetCategories = GetCategories(readCategoriesRepository)

        val presenter = RestCategoryListPresenter()
        getCategories.executeWith(GetCategoriesInput(actor), presenter)

        return presenter.presentation!!
    }

    @PostMapping("/transactions/{id}/approve")
    fun approveTransaction(
            @PathVariable("id") transactionId: String,
            @RequestParam("actor") actor: Actor
    ) {

        val getTransaction: IGetTransaction = GetTransaction(transactionRepository)
        val getTransactionPresenter = RestTransactionPresenter()
        getTransaction.executeWith(actor, transactionId, getTransactionPresenter)
        val transaction = getTransactionPresenter.presentation ?:
            throw HttpClientErrorException.NotFound.create(
                    HttpStatus.NOT_FOUND, "Transaction not found",
                    HttpHeaders.EMPTY, ByteArray(0),
                    Charset.defaultCharset())



        val approveTransaction : IApproveTransaction = ApproveTransaction(transactionRepository, InMemoryAuditLogRepository())
    }
}