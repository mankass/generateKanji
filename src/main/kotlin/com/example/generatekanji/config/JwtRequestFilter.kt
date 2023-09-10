package com.example.generatekanji.config

import com.example.generatekanji.application.utils.JwtUtils
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors

@Component
class JwtRequestFilter(val jwtUtils: JwtUtils) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        var username: String? = null
        var jwt: String? = null

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7)
            try {
                username = jwtUtils.getUsername(jwt)
            } catch (e: ExpiredJwtException) {
                logger.debug("Время жизни токены вышло")
            } catch (e: SignatureException) {
                logger.debug("Подпись неверная")
            }
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val token = UsernamePasswordAuthenticationToken(
                username,
                null,
                jwt?.let {
                    jwtUtils.getRoles(it).stream().map { x -> SimpleGrantedAuthority(x) }.collect(Collectors.toList())
                },
            )
            SecurityContextHolder.getContext().authentication = token
        }
        filterChain.doFilter(request, response)
    }
}