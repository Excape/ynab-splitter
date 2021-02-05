package ch.excape.ynabsplitter.rest.security.clientrepository

import ch.excape.ynabsplitter.rest.security.clientrepository.OAuth2AuthorizedClientEntity.AuthorizedClientId
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository

class MongoOAuth2AuthorizedClientService(
        private val crudRepository: OAuth2AuthorizedClientCrudRepository,
        private val clientRepo: ClientRegistrationRepository
) : OAuth2AuthorizedClientService {

    override fun <T : OAuth2AuthorizedClient?> loadAuthorizedClient(clientRegistrationId: String?, principalName: String?): T? {
        if (clientRegistrationId == null || principalName == null) return null
        val authorizedClient = crudRepository.findByIdOrNull(AuthorizedClientId(clientRegistrationId, principalName))
                ?.toAuthorizedClient(clientRepo)
        return authorizedClient as T
    }

    override fun saveAuthorizedClient(authorizedClient: OAuth2AuthorizedClient, principal: Authentication?) {
        crudRepository.save(authorizedClient.toEntity())
    }

    override fun removeAuthorizedClient(clientRegistrationId: String, principalName: String) {
        crudRepository.deleteById(AuthorizedClientId(clientRegistrationId, principalName))
    }
}

interface OAuth2AuthorizedClientCrudRepository
    : CrudRepository<OAuth2AuthorizedClientEntity, AuthorizedClientId> {

}