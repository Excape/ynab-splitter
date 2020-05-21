package ch.excape.ynabsplitter.adapter.persistence

import ch.excape.ynabsplitter.application.outbound_ports.persistence.AuditLogRepository
import ch.excape.ynabsplitter.domain.AuditLog

class RedisAuditLogRepository(private val redisCrudRepository: AuditLogCrudRepository) : AuditLogRepository {
    override fun saveAuditLog(auditLog: AuditLog) {
       redisCrudRepository.save(auditLog.toEntity())
    }

    override fun getAllAuditLogs(): List<AuditLog> = redisCrudRepository.findAll().map { it.toDomain() }
}