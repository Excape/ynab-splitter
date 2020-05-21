package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.domain.Actor
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.server.ResponseStatusException
import java.lang.RuntimeException
import javax.servlet.http.HttpServletRequest

@Service
@Profile("prod")
class LoginManagerProd: LoginManager {

    private val userNameMap: Map<String, Actor> = mapOf(
            "excape" to Actor.ROBIN,
            "sophie" to Actor.SOPHIE
    )
    override fun getLoggedInActor(request: HttpServletRequest): Actor {
        val session = request.getSession(false) ?:
            throw RuntimeException("X-Forwarded-User header must be set")
        val userAttribute = session.getAttribute(USER_ATTRIBUTE).toString()

        return userNameMap[userAttribute] ?:
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized")
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
