package com.example.practice.domain.user.service

import com.example.practice.domain.user.entity.User
import com.example.practice.domain.user.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
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
        val user : User? = userRepository.findByUserid(userid)
        if(user===null)
            throw IllegalArgumentException("아이디나 비밀번호가 잘못되었습니다.")

      //  return createUserDetails(user)
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

