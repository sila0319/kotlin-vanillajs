package com.example.practice.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig() {
    private val allowedUrl = arrayOf("/**","/swagger-ui/**","/v3/api-docs/**","/api/v1/member/login","/api/v1/member/logout","/api/v1/member/createMember") //permitall할 대상을 정리한 구역
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
                http.authorizeHttpRequests{
                    it.requestMatchers(*allowedUrl).permitAll()
                                .anyRequest().authenticated()
                }
                .csrf().disable()
                .headers()
                .addHeaderWriter(XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        return http.build()
    }

    //패스워드 암호화하는 부분을 빈으로 처리한다.
    //암호화 방식을 변경하면 BcyptPasswordEncoder를 일일이 찾아서 수정해야하기 때문이다.
    @Bean
    fun passwordEncoder () = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun  //스프링 시큐리티의 인증을 담당하며 사용자의 로그인상태를 점검한다.
            authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.getAuthenticationManager()
    }
//    @Bean
//    fun filterChain(http: HttpSecurity) = http
//            .authorizeHttpRequests{
//                it.requestMatchers(*allowedUrl).permitAll()
//                        .anyRequest().authenticated()
//            }
//            .formLogin()
//            .loginPage("/member/login")
//            .defaultSuccessUrl("/")
//
//            .and()
//            .logout()
//            .logoutRequestMatcher(AntPathRequestMatcher("member/logout"))
//            .logoutSuccessUrl("/")
//            .invalidateHttpSession(true)
//            .and()
//            .csrf().disable()
//            .build()
//
//    @Bean
//    fun passwordEncoder () = BCryptPasswordEncoder()
//    @Bean
//    @Throws(Exception::class)
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
//        return authenticationConfiguration.getAuthenticationManager()
//    }
}
