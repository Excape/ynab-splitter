package ch.excape.ynabsplitter.adapter.persistence.user

import org.springframework.data.repository.CrudRepository

interface UserCrudRepository : CrudRepository<UserEntity, String>