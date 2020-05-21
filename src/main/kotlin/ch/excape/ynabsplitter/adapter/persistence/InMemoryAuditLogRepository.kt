package ch.excape.ynabsplitter.adapter.persistence

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.domain.AuditLog

class InMemoryAuditLogRepository : AuditLogRepository {

    private val auditLogs: MutableList<AuditLog> = mutableListOf()

    override fun saveAuditLog(auditLog: AuditLog) {
        auditLogs.add(auditLog)
    }

    override fun getAllAuditLogs(): List<AuditLog> {
        return auditLogs.toList()
    }
}

