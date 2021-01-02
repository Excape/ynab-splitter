package ch.excape.ynabsplitter.adapter.persistence.auditlog

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

interface AuditLogMongoCrudRepository : MongoRepository<AuditLogEntity, String>