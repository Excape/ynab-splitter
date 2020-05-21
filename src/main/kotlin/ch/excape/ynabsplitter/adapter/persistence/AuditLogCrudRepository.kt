package ch.excape.ynabsplitter.adapter.persistence

import org.springframework.data.repository.CrudRepository

interface AuditLogCrudRepository : CrudRepository<AuditLogEntity, String> {
}