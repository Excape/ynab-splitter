package ch.excape.ynabsplitter.rest.security

import ch.excape.ynabsplitter.rest.security.clientrepository.MongoOAuth2AuthorizedClientService
import ch.excape.ynabsplitter.rest.security.clientrepository.OAuth2AuthorizedClientCrudRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.session.web.http.CookieSerializer
import org.springframework.session.web.http.DefaultCookieSerializer


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val mongoRepo: OAuth2AuthorizedClientCrudRepository,
        private val clientRepo: ClientRegistrationRepository
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http {
            authorizeRequests {
                authorize("/service-worker.js", permitAll)
                // allow manifest access for service worker
                authorize("/manifest.json", permitAll)
                authorize("/logo*.png", permitAll)
                authorize(anyRequest, authenticated)
            }
            oauth2Login {
                userInfoEndpoint {
                    userService = oauth2UserService()
                }
            }
            oauth2Client {
            }
            csrf {
                csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse()
            }
        }
    }

    @Bean
    fun oauth2UserService(): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        return CustomOAuth2UserService()
    }

    @Bean
    fun mongoAuthorizedClientService(crudRepository: OAuth2AuthorizedClientCrudRepository, clientRepo: ClientRegistrationRepository): MongoOAuth2AuthorizedClientService {
        return MongoOAuth2AuthorizedClientService(crudRepository, clientRepo)
    }

    // use a new instance of authorizedClientService to avoid circular reference (unclear why)
    @Bean
    fun authorizedClientRepository(): OAuth2AuthorizedClientRepository? {
        return AuthenticatedPrincipalOAuth2AuthorizedClientRepository(mongoAuthorizedClientService(mongoRepo, clientRepo))
    }

    /**
     * Overriding the Session cookie to set the max age.
     */
    @Bean
    fun cookieSerializer(): CookieSerializer {
        val serializer = DefaultCookieSerializer()
        serializer.setCookieName("SESSION")
        serializer.setCookiePath("/")
        serializer.setCookieMaxAge(7 * 24 * 3600)
        return serializer
    }
}