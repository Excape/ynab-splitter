package ch.excape.ynabsplitter.rest.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2UserService : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private val delegate = DefaultOAuth2UserService()

    private val USER_ID = "userId"

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        // Delegate to the default implementation for loading a user
        val oAuth2User = delegate.loadUser(userRequest)

        val userId = extractUserId(oAuth2User)
        val attributes = mapOf(USER_ID to userId)

        // assign a simple user role for now
        val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))

        return DefaultOAuth2User(authorities, attributes, USER_ID)

    }

    /**
     * extract the user id according to the response model
     * see https://api.youneedabudget.com/v1#/User/getUser
     */
    private fun extractUserId(oAuth2User: OAuth2User): String {
        val dataAttribute = oAuth2User.attributes["data"] as Map<*, *>
        val userAttribute = dataAttribute["user"] as Map<*, *>
        return userAttribute["id"] as String
    }
}