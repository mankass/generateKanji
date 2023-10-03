package com.example.generatekanji.config

import com.example.generatekanji.application.services.UserService
import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(val userService: UserService,val jwtRequestFilter: JwtRequestFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/signin", "/signup").permitAll()
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("web-client/**").permitAll()
                .requestMatchers("/api").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/v2/api-docs",
                    "/v3/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/api-docs/**").permitAll()
                .anyRequest().permitAll()

        }
            .formLogin { formLogin ->
                formLogin
                    .loginPage("/signin")
                    .usernameParameter("login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            }
            .rememberMe { rememberme -> rememberme.key("123") }
            .cors{cors->cors.disable()}
            .csrf{csrf->csrf.disable()}
            .logout { logout -> logout.logoutUrl("/signout").permitAll() }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { ex -> ex.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
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