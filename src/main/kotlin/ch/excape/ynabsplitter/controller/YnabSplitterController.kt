package ch.excape.ynabsplitter.controller

import ch.excape.ynabsplitter.domain.RobinActor
import ch.excape.ynabsplitter.domain.Transaction
import ch.excape.ynabsplitter.service.YnabService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class YnabSplitterController(private val ynabService: YnabService) {
    @GetMapping("/transactions")
    fun getBudgets(): List<Transaction> {
        return ynabService.getAllTransactions(RobinActor())
    }
}