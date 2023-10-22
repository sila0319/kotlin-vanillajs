package com.example.practice.domain.user.service

import com.example.practice.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SignService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userid: String?): UserDetails {
        val user = userRepository.findByUserid(userid)
        if(user===null)
            throw IllegalArgumentException("중복된 id 입니다.")

        return createUserDetails(user)
    }

    private fun createUserDetails(user: com.example.practice.domain.user.entity.User?): UserDetails {
        val authorities = user?.role?.let { listOf(it.name) } ?: emptyList()
        return org.springframework.security.core.userdetails.User(
            user?.userid,
            user?.userpw,
            authorities.map { SimpleGrantedAuthority("ROLE_$it") }
        )
    }
}
