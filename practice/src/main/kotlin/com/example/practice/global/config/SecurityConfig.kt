package com.example.practice.global.config

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig() {
    private val allowedUrl = arrayOf("/**","/swagger-ui/**","/v3/api-docs/**","/api/v1/user/login","/api/v1/user/logout") //permitall할 대상을 정리한 구역

    @Bean
    fun filterChain(http: HttpSecurity) = http
            .authorizeHttpRequests{
                it.requestMatchers(*allowedUrl).permitAll()
                        .anyRequest().authenticated()
            }
            .formLogin()
            .loginPage("/user/login")
            .defaultSuccessUrl("/")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .and()
            .csrf().disable()
            .build()

    @Bean
    fun passwordEncoder () = BCryptPasswordEncoder()
}
