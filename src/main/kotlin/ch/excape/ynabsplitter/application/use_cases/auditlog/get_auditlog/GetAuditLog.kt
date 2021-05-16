package ch.excape.ynabsplitter.application.use_cases.auditlog.get_auditlog

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.application.outbound_ports.presentation.SingleAuditLogPresenter
import ch.excape.ynabsplitter.application.use_cases.auditlog.get_auditlog.ports.IGetAuditLog

class GetAuditLog(private val auditLogRepository: AuditLogRepository) : IGetAuditLog {
    override fun executeWith(auditLogId: String, presenter: SingleAuditLogPresenter) {
        val auditLog = auditLogRepository.getById(auditLogId)
        presenter.present(auditLog ?: throw IllegalArgumentException("Audit log with id $auditLogId not found"))
    }
}
