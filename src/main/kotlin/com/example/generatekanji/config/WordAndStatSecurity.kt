package com.example.generatekanji.config

import com.example.generatekanji.application.services.UserService
import com.example.generatekanji.application.services.WordAndStatService
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class WordAndStatSecurity(val wordAndStatService: WordAndStatService, val userService: UserService) :
    AuthorizationManager<RequestAuthorizationContext> {
    override fun check(
        authentication: Supplier<Authentication>?,
        ctx: RequestAuthorizationContext?
    ): AuthorizationDecision? {

        val wordAndStatId: String? = ctx?.variables?.get("wordAndStatId")
        val auth = authentication?.get()
        if (auth?.authorities?.any { it.equals(SimpleGrantedAuthority("ADMIN")) } == true) {
            return AuthorizationDecision(true)
        }
        return AuthorizationDecision(isOwner(auth, wordAndStatId))
    }

    fun isOwner(authentication: Authentication?, id: String?): Boolean {
        if (id != null) {
            val wordAndStat = wordAndStatService.findById(id)
            val user = userService.getByLogin(authentication?.principal.toString())
            return wordAndStat.userMinifiedView.id == user.id
        }
        return false
    }
}