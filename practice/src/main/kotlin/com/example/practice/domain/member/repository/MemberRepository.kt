package com.example.practice.domain.member.repository

import com.example.practice.domain.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member?, Long?>{

    fun findByMemberid(memberid: String?) : Member?

    fun findById(id : Long?) : Member?

    override fun findAll(pageable: Pageable) : Page<Member?>


}