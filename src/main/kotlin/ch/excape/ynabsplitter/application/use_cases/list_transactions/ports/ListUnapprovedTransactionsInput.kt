package ch.excape.ynabsplitter.application.use_cases.list_transactions.ports

import ch.excape.ynabsplitter.domain.Actor

data class ListUnapprovedTransactionsInput(
        val actors: List<Actor>
)
