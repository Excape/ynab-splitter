package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.domain.Actor
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest

@Service
@Profile("prod")
class LoginManagerProd: LoginManager {

    private val logger = LoggerFactory.getLogger(LoginManagerProd::class.java)

    private val userNameMap: Map<String, Actor> = mapOf(
            "excape" to Actor.ROBIN,
            "sophie" to Actor.SOPHIE
    )
    override fun getLoggedInActor(request: HttpServletRequest): Actor {
        val userAttribute = request
                .getSession(false)
                ?.getAttribute(USER_ATTRIBUTE)
                ?.toString() ?:
            throw RuntimeException("X-Forwarded-User header must be set")

        val actor = userNameMap[userAttribute]
        return if (actor != null) actor else {
            logger.error("Unauthorized user: $userAttribute")
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized")
        }
    }
}

@Service
@Profile("dev")
class LoginManagerDev: LoginManager {
    override fun getLoggedInActor(request: HttpServletRequest): Actor {
        // for testing
        return Actor.ROBIN
    }

}

interface LoginManager {
    fun getLoggedInActor(request: HttpServletRequest): Actor
}
