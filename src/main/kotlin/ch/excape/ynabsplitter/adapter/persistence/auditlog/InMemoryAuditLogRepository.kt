package ch.excape.ynabsplitter.adapter.persistence.auditlog

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.domain.AuditLog

class InMemoryAuditLogRepository : AuditLogRepository {

    private val auditLogs: MutableList<AuditLog> = mutableListOf()
    override fun getById(auditLogId: String): AuditLog? {
        return auditLogs.find { it.id == auditLogId }
    }

    override fun delete(auditLogId: String) {
        auditLogs.removeIf { it.id == auditLogId }
    }

    override fun saveAuditLog(auditLog: AuditLog) {
        auditLogs.add(auditLog)
    }

    override fun getAllAuditLogs(): List<AuditLog> {
        return auditLogs.toList()
    }
}

