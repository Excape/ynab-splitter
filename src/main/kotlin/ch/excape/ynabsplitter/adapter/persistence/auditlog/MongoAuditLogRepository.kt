package ch.excape.ynabsplitter.adapter.persistence.auditlog

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.domain.AuditLog

class MongoAuditLogRepository(private val mongoRepo: AuditLogMongoCrudRepository) : AuditLogRepository {
    override fun saveAuditLog(auditLog: AuditLog) {
        mongoRepo.save(auditLog.toEntity())
    }

    override fun getAllAuditLogs(): List<AuditLog> =
            mongoRepo.findAll()
                    .map { it.toDomain() }
}