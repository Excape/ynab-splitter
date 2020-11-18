package ch.excape.ynabsplitter.adapter.persistence.auditlog

import org.springframework.data.repository.CrudRepository

interface AuditLogCrudRepository : CrudRepository<AuditLogEntity, String>