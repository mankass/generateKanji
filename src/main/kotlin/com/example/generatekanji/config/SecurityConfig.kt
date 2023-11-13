package com.example.generatekanji.config

import com.example.generatekanji.application.services.UserService
import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@CrossOrigin
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfig(val userService: UserService, val jwtRequestFilter: JwtRequestFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/signin", "/signup").permitAll()
                .requestMatchers("/api/web-client/wordAndStat/**").permitAll()
                .requestMatchers("/api/web-client/deck/**").authenticated()
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("/api/web-client/security/**").permitAll()
                .requestMatchers("/api/security/**").permitAll()
                .requestMatchers("/api/web-client/**").permitAll()
                .requestMatchers("/v2/api-docs",
                    "/v3/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/api-docs/**"
                ).permitAll()
        }
            .formLogin { formLogin ->
                formLogin
                    .loginPage("/signin")
                    .usernameParameter("login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            }
            .rememberMe { rememberme -> rememberme.key("123") }
            .cors { }
            .csrf { csrf -> csrf.disable() }
            .logout { logout -> logout.logoutUrl("/signout").permitAll() }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { ex -> ex.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    open fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:9000")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userService)
        return daoAuthenticationProvider
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationManager: AuthenticationConfiguration): AuthenticationManager {
        return authenticationManager.authenticationManager
    }

}