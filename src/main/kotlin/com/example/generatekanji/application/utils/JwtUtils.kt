package com.example.generatekanji.application.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import java.util.stream.Collectors

@Service
class JwtUtils(
    @Value("\${jwt.secret}")
    var secret: String,
    @Value("\${jwt.lifitime}")
    var lifiteme: Duration
) {

    fun generateToken(user: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        val roles = (user.authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        claims["roles"] = roles
        val issuedDate = Date()
        val expiredDate = Date(issuedDate.time + lifiteme.toMillis())
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.username)
            .setIssuedAt(issuedDate)
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()

    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    fun getUsername(token: String): String {
        return getClaims(token).subject
    }

    fun getRoles(token: String): List<String> {
        return getClaims(token)["roles"] as List<String>
    }
}