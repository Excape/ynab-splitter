package ch.excape.ynabsplitter.adapter.persistence.auditlog.mongo

import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.MongoRepository

@Profile("prod")
interface AuditLogMongoCrudRepository : MongoRepository<AuditLogEntity, String>
