package com.example.practice.domain.member.service

import com.example.practice.domain.member.entity.Member
import com.example.practice.domain.member.entity.Role
import com.example.practice.domain.member.repository.MemberRepository
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
class SignService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(memberId: String?): UserDetails {
        val member : Member? = memberRepository.findByMemberid("test4")
        println(member)
        println(memberId)
        if(member===null)
            throw IllegalArgumentException("아이디나 비밀번호가 잘못되었습니다.")

        val authorities: MutableList<GrantedAuthority> = mutableListOf()
        if ("ADMIN" == member.role.toString()) {
            authorities.add(SimpleGrantedAuthority(Role.ADMIN.toString()))
        } else if("MEMBER" == member.role.toString()) {
            authorities.add(SimpleGrantedAuthority(Role.MEMBER.toString()))
        }else{
            throw IllegalArgumentException("로그인 할수 없는 유저 입니다.")
        }
        val user : User = User(member.memberid, member.memberpw, authorities)
        println(user.username)
        return user
    }
}

