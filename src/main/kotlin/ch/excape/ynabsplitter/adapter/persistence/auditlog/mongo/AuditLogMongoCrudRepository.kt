package ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo

import org.springframework.data.mongodb.repository.MongoRepository

interface AuditLogMongoCrudRepository : MongoRepository<AuditLogEntity, String>