package ch.excape.ynabsplitter.rest.security.clientrepository

import ch.excape.ynabsplitter.rest.security.clientrepository.OAuth2AuthorizedClientEntity.*
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.OAuth2RefreshToken
import java.time.Instant

@Document(collection = "authorized_clients")
data class OAuth2AuthorizedClientEntity(
        val id: AuthorizedClientId,
        val accessToken: AccessToken,
        val refreshToken: RefreshToken?,
        val createdAt: Instant
) {
    data class AuthorizedClientId(
            val clientRegistrationId: String,
            val principalName: String
    )

    data class AccessToken(
            val value: String,
            val issuedAt: Instant?,
            val expiresAt: Instant?,
    )

    data class RefreshToken(
            val value: String,
            val issuedAt: Instant?
    )
}

fun OAuth2AuthorizedClient.toEntity() =
        OAuth2AuthorizedClientEntity(
                AuthorizedClientId(clientRegistration.registrationId, principalName),
                AccessToken(accessToken.tokenValue, accessToken.issuedAt, accessToken.expiresAt),
                if (refreshToken != null) RefreshToken(refreshToken!!.tokenValue, refreshToken?.issuedAt) else null,
                createdAt = Instant.now()
        )

fun OAuth2AuthorizedClientEntity.toAuthorizedClient(clientRepo: ClientRegistrationRepository) =
        OAuth2AuthorizedClient(
                getClientRegistration(id.clientRegistrationId, clientRepo),
                id.principalName,
                OAuth2AccessToken(
                        OAuth2AccessToken.TokenType.BEARER,
                        accessToken.value,
                        accessToken.issuedAt,
                        accessToken.expiresAt
                ),
                if (refreshToken != null)
                    OAuth2RefreshToken(
                            refreshToken.value,
                            refreshToken.issuedAt
                    ) else null

        )

fun getClientRegistration(id: String, clientRepo: ClientRegistrationRepository) =
        clientRepo.findByRegistrationId(id) ?: throw IllegalArgumentException("Client registration $id not found")
