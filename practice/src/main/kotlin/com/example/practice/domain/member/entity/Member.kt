package com.example.practice.domain.member.entity

import com.example.practice.domain.member.dto.*
import com.example.practice.global.entity.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

enum class Role{
    MEMBER,ADMIN,BEN,DROP
}
@Entity
@Table(name = "members")
class Member (

    memberid: String,
    memberpw : String,
    membername : String,
    role: Role

): AuditingEntity (){
    @Column(nullable = false)
    var memberid : String = memberid
        protected set

    @Column(nullable = false)
    var memberpw : String = memberpw
        protected set

    @Column(nullable = false)
    var membername : String = membername
        protected set

    @Enumerated(EnumType.STRING)
    var role : Role = role
        protected set

    fun updateInfo(req: MemberSaveReq) {
        this.memberid = req.memberid
        this.memberpw = req.memberpw
        this.membername = req.membername
        this.role = req.role!!

    }
}

/**
 * 사용자 요청 값을 dto로 변환하여 보여주기 위함.
 * 회원생성, 회원정보변경시 사용할 dto
 * */
fun Member.tomemberSaveRes() : MemberSaveRes {
    return MemberSaveRes(
        id = this.id,
        memberid = this.memberid,
        memberpw = this.memberpw,
        membername = this.membername,
        role = this.role

    )
}



/**
 * 사용자 요청 값을 dto로 변환하여 보여주기 위함.
 * 사용자 모든 정보를 담아서 보여주기 위한 dto
 * */
fun Member.tomemberRes() : MemberRes {
    return MemberRes(
        id = this.id,
        memberid = this.memberid,
        memberpw = this.memberpw,
        membername = this.membername,
        role = this.role,
    )
}


