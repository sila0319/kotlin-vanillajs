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
        .formLogin().disable()
        .csrf().disable()
        .cors()
        .and()
        .authorizeHttpRequests {
            it.requestMatchers(*allowedUrl).permitAll()	// requestMatchers의 인자로 전달된 url은 모두에게 허용
                .anyRequest().authenticated()	// 그 외의 모든 요청은 인증 필요
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }	// 세션을 사용하지 않으므로 STATELESS 설정
        .build()!!

    @Bean
    fun passwordEncoder () = BCryptPasswordEncoder()
}
