package ch.excape.ynabsplitter.application.outbound_ports.persistence

import ch.excape.ynabsplitter.domain.AuditLog

interface AuditLogRepository {
    fun saveAuditLog(auditLog: AuditLog)
}