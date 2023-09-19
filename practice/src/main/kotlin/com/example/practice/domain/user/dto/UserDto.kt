package com.example.practice.domain.user.dto

import com.example.practice.domain.user.entity.Role
import com.example.practice.domain.user.entity.User
import org.springframework.stereotype.Component

data class UserSaveReq(
    var id : Long?,
    var userid: String,
    var userpw : String,
    var username : String,
    var role : Role,
)

/**
 * 사용자 요청을 Entity로 변환하는 것
 * 수정과, 생성으로 사용할 예정
 * */
fun UserSaveReq.toEntity() : User{
    return User(
        userid = this.userid,
        userpw = this.userpw,
        username = this.username,
        role = this.role
    )
}

/**
 *  Entity를 사용자 요청 결과 값으로 변환하는 것
 * 회원 가입, 혹은 회원정보 수정에 사용할 dto
 * */
data class UserSaveRes(
    var id : Long?,
    var userid: String,
    var userpw : String,
    var username : String,
    var role : Role,
)


/**
 * entity를 사용자 요청 결과값으로 변환하는 것
 * User가 담고 있는 모든 값을 return 하는 dto
 * */
data class UserRes(
    var id : Long?,
    var userid: String,
    var userpw : String,
    var username : String,
    var role : Role,
)

data class UserReq(
    var id : Long?,
    var userid: String,
    var userpw : String,
    var username : String,
    var role : Role,
)
fun UserReq.toEntity() : User{
    return User(
        userid = this.userid,
        userpw = this.userpw,
        username = this.username,
        role = this.role,
    )
}


data class UserDeleteReq(
    var id : Long? ,
    var userid: String,
    var userpw : String,
)
fun UserDeleteReq.checkUser(user:User) : Boolean{
    if(id!!.equals(user.id) && userid.equals(user.userid) && userpw.equals(user.userpw)){
        return true
    }else{
        return false
    }
}


data class UserLoginReq (
    var userid : String,
    var userpw : String,
)

data class UserLoginRes(
    val id : Long ,
    var userid : String,
    var userpw : String,
)


