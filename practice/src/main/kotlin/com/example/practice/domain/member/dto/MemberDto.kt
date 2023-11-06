package com.example.practice.domain.member.dto

import com.example.practice.domain.member.entity.Role
import com.example.practice.domain.member.entity.Member

data class MemberSaveReq(
    var id : Long?,
    var memberid: String,
    var memberpw : String,
    var membername : String,
    var memberpwchk : String,
    var role : Role?,
)

/**
 * 사용자 요청을 Entity로 변환하는 것
 * 수정과, 생성으로 사용할 예정
 * */
fun MemberSaveReq.toEntity() : Member{
    if(this.role==null){
        this.role = Role.MEMBER;
    }
    return Member(
        memberid = this.memberid,
        memberpw = this.memberpw,
        membername = this.membername,
        role = this.role!!
    )
}

/**
 *  Entity를 사용자 요청 결과 값으로 변환하는 것
 * 회원 가입, 혹은 회원정보 수정에 사용할 dto
 * */
data class MemberSaveRes(
    var id : Long?,
    var memberid: String,
    var memberpw : String,
    var membername : String,
    var role : Role,
)


/**
 * entity를 사용자 요청 결과값으로 변환하는 것
 * member가 담고 있는 모든 값을 return 하는 dto
 * */
data class MemberRes(
    var id : Long?,
    var memberid: String,
    var memberpw : String,
    var membername : String,
    var role : Role,
)

data class MemberReq(
    var id : Long?,
    var memberid: String,
    var memberpw : String,
    var membername : String,
    var role : Role,
)
fun MemberReq.toEntity() : Member{
    return Member(
        memberid = this.memberid,
        memberpw = this.memberpw,
        membername = this.membername,
        role = this.role,
    )
}


data class MemberDeleteReq(
    var id : Long? ,
    var memberid: String,
    var memberpw : String,
)
fun MemberDeleteReq.checkmember(member:Member) : Boolean{
    if(id!!.equals(member.id) && memberid.equals(member.memberid) && memberpw.equals(member.memberpw)){
        return true
    }else{
        return false
    }
}


data class MemberLoginReq (
    var memberid : String,
    var memberpw : String,
)

data class MemberLoginRes(
    val id : Long ,
    var memberid : String,
    var memberpw : String,
)


