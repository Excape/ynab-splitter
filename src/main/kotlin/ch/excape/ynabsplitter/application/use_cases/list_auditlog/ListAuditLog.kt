package ch.excape.ynabsplitter.application.use_cases.list_transactions

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.AuditLogPresenter
import ch.excape.ynabsplitter.application.outbound_ports.presentation.TransactionListPresenter
import ch.excape.ynabsplitter.application.outbound_ports.ynab.ReadTransactionsRepository
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.IListAuditLog
import ch.excape.ynabsplitter.application.use_cases.list_transactions.ports.ListUnapprovedTransactionsInput

class ListAuditLog(private val auditLogRepository: AuditLogRepository) : IListAuditLog {
    override fun executeWith(presenter: AuditLogPresenter) {
        val allAuditLogs = auditLogRepository.getAllAuditLogs()
        presenter.present(allAuditLogs)
    }
}
