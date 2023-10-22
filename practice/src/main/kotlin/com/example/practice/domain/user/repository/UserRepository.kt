package com.example.practice.domain.user.repository

import com.example.practice.domain.user.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User?, Long?>{

    fun findByUserid(userid: String?) : User?

    fun findById(id : Long?) : User?

    override fun findAll(pageable: Pageable) : Page<User?>


}