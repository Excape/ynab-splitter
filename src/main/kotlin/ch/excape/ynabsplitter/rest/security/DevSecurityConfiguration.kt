package ch.excape.ynabsplitter.rest.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService


@Configuration
@EnableWebSecurity
@Profile("dev")
class DevSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(MyUserDetailsService())
    }

    override fun configure(http: HttpSecurity?) {
        http {
            authorizeRequests {
                authorize("/api/**", authenticated)
            }
            httpBasic {
                realmName = "Enter any username and no password"
            }
            csrf { disable() }
        }
    }
}

class MyUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        // this allows any user with an empty password
        return User(username, "{noop}", listOf(SimpleGrantedAuthority("ROLE_USER")))
    }

}
