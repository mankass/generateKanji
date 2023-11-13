package com.example.generatekanji.config

import com.example.generatekanji.application.services.DeckService
import com.example.generatekanji.application.services.UserService
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class UserSecurity(val deckService: DeckService, val userService: UserService) :
    AuthorizationManager<RequestAuthorizationContext> {
    override fun check(
        authentication: Supplier<Authentication>?,
        ctx: RequestAuthorizationContext?
    ): AuthorizationDecision? {

        val deckId: String? = ctx?.variables?.get("deckId")
        val auth = authentication?.get()
        if (auth?.authorities?.any { it.equals(SimpleGrantedAuthority("ADMIN")) } == true) {
            return AuthorizationDecision(true)
        }
        return AuthorizationDecision(isOwner(auth, deckId))
    }

    fun isOwner(authentication: Authentication?, deckId: String?): Boolean {
        if (deckId != null) {
            val deck = deckService.deckRepository.findById(deckId).get().listUsers
            val user = userService.getByLogin(authentication?.principal.toString())
            if (deck != null) {
                return deck.any { it.id == user.id }
            }
        }
        return true
    }
}