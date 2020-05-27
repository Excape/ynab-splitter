package ch.excape.ynabsplitter.rest

import ch.excape.ynabsplitter.domain.Actor
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val USER_ATTRIBUTE: String = "USER_NAME"

@Configuration
class SecurityContextConfigurer : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(UsernameInterceptor())
    }
}

class UsernameInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userHeader = request.getHeader("X-Forwarded-User")
        if (userHeader != null && userHeader.isNotEmpty()) {
            val session = request.getSession(true)
            session.setAttribute(USER_ATTRIBUTE, userHeader)
        }
        return true
    }
}