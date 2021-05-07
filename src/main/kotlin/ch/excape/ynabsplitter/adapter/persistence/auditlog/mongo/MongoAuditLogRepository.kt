package ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.domain.AuditLog
import org.springframework.data.repository.findByIdOrNull

class MongoAuditLogRepository(private val mongoRepo: AuditLogMongoCrudRepository) : AuditLogRepository {
    override fun saveAuditLog(auditLog: AuditLog) {
        mongoRepo.save(auditLog.toEntity())
    }

    override fun delete(auditLogId: String) {
        mongoRepo.deleteById(auditLogId)
    }

    override fun getById(auditLogId: String): AuditLog? =
            mongoRepo.findByIdOrNull(auditLogId)?.toDomain()

    override fun getAllAuditLogs(): List<AuditLog> =
            mongoRepo.findAll()
                    .map { it.toDomain() }
}