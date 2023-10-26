package com.example.practice.domain.user.service

import com.example.practice.domain.user.dto.*
import com.example.practice.domain.user.entity.*
import com.example.practice.domain.user.repository.UserRepository
import com.example.practice.global.exception.UserNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(

    private val userRepository: UserRepository,
        private val singServie : SignService,
){

    @Transactional(readOnly = true)
    fun getAllUser(pageable : Pageable) : Page<UserRes> =
        userRepository.findAll(pageable).map{
        it?.toUserRes()
    }


    @Transactional(readOnly = true)
    fun getUser(id : Long?) : UserRes? {
        var user : User? = userRepository.findById(id) ?: throw UserNotFoundException(id)
        return user?.toUserRes()
    }
    @Transactional
    fun createUser(userSaveReq: UserSaveReq)  : UserSaveRes {
        if( userRepository.findByUserid(userSaveReq.userid) === null){
            if(userSaveReq.userpw != userSaveReq.userpwchk)
                throw IllegalArgumentException("비밀번호를 확인 해주세요.")
            var user : User = userSaveReq.toEntity()
            userRepository.save(user)
            return user.toUserSaveRes()
        }else {
            throw IllegalArgumentException("중복된 id 입니다.")
        }
    }
    @Transactional
    fun updateUser(userSaveReq : UserSaveReq) : UserSaveRes{
        var user: User? = userRepository.findById(userSaveReq.id) ?: throw UserNotFoundException(userSaveReq.id)
        user!!.updateInfo(userSaveReq)
        userRepository.save(user)
        return user.toUserSaveRes()
    }

    @Transactional
    fun deleteUser(id : Long,userDeleteReq: UserDeleteReq) : String{
        if(id.equals(userDeleteReq.id)){
            var user : User? = userRepository.findById(userDeleteReq.id) ?: throw UserNotFoundException(userDeleteReq.id)
            if(userDeleteReq.checkUser(user!!)) {
                userRepository.delete(user!!)
                return "회원 탈퇴 완료 되었습니다."
            }else
                return "잘못된 요청입니다. 다시 확인해 주세요"
        }else{
            return "잘못된 정보가 기입되어 있습니다."
        }
    }
//    @Transactional
//    fun loginUser(userid: String ,userpw: String) : String {
//        val authenticationToken = UsernamePasswordAuthenticationToken(userid, userpw);
//        singServie.loadUserByUsername(userid);
//
//    }
}

