package com.example.practice.domain.user.entity

import com.example.practice.domain.user.dto.*
import com.example.practice.global.entity.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

enum class Role{
    USER,ADMIN,BEN,DROP
}
@Entity
@Table(name = "users")
class User (

    userid: String,
    userpw : String,
    username : String,
    role: Role

): AuditingEntity (){
    @Column(nullable = false)
    var userid : String = userid
        protected set

    @Column(nullable = false)
    var userpw : String = userpw
        protected set

    @Column(nullable = false)
    var username : String = username
        protected set

    @Enumerated(EnumType.STRING)
    var role : Role = role
        protected set

    fun updateInfo(req: UserSaveReq) {
        this.userid = req.userid
        this.userpw = req.userpw
        this.username = req.username
        this.role = req.role

    }
}

/**
 * 사용자 요청 값을 dto로 변환하여 보여주기 위함.
 * 회원생성, 회원정보변경시 사용할 dto
 * */
fun User.toUserSaveRes() : UserSaveRes {
    return UserSaveRes(
        id = this.id,
        userid = this.userid,
        userpw = this.userpw,
        username = this.username,
        role = this.role

    )
}



/**
 * 사용자 요청 값을 dto로 변환하여 보여주기 위함.
 * 사용자 모든 정보를 담아서 보여주기 위한 dto
 * */
fun User.toUserRes() : UserRes {
    return UserRes(
        id = this.id,
        userid = this.userid,
        userpw = this.userpw,
        username = this.username,
        role = this.role,
    )
}


