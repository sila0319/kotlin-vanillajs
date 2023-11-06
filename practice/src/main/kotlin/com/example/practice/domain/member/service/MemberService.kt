package com.example.practice.domain.member.service

import com.example.practice.domain.member.dto.*
import com.example.practice.domain.member.entity.*
import com.example.practice.domain.member.repository.MemberRepository
import com.example.practice.global.exception.UserNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MemberService(

    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder

){

    @Transactional(readOnly = true)
    fun getAllmember(pageable : Pageable) : Page<MemberRes> =
        memberRepository.findAll(pageable).map{
        it?.tomemberRes()
    }


    @Transactional(readOnly = true)
    fun getmember(id : Long?) : MemberRes? {
        var member : Member? = memberRepository.findById(id) ?: throw UserNotFoundException(id)
        return member?.tomemberRes()
    }
    @Transactional
    fun createmember(memberSaveReq: MemberSaveReq): MemberSaveRes {
        if (memberRepository.findByMemberid(memberSaveReq.memberid) === null) {
            if (memberSaveReq.memberpw != memberSaveReq.memberpwchk)
                throw IllegalArgumentException("비밀번호를 확인 해주세요.")

            val encodedPassword = passwordEncoder.encode(memberSaveReq.memberpw) // 비밀번호 암호화
            memberSaveReq.memberpw = encodedPassword;
            val member: Member = memberSaveReq.toEntity()
            memberRepository.save(member)
            return member.tomemberSaveRes()
        } else {
            throw IllegalArgumentException("중복된 id 입니다.")
        }
    }

    @Transactional
    fun updatemember(memberSaveReq : MemberSaveReq) : MemberSaveRes{
        var member: Member? = memberRepository.findById(memberSaveReq.id) ?: throw UserNotFoundException(memberSaveReq.id)
        member!!.updateInfo(memberSaveReq)
        memberRepository.save(member)
        return member.tomemberSaveRes()
    }

    @Transactional
    fun deletemember(id : Long,memberDeleteReq: MemberDeleteReq) : String{
        if(id.equals(memberDeleteReq.id)){
            var member : Member? = memberRepository.findById(memberDeleteReq.id) ?: throw UserNotFoundException(memberDeleteReq.id)
            if(memberDeleteReq.checkmember(member!!)) {
                memberRepository.delete(member!!)
                return "회원 탈퇴 완료 되었습니다."
            }else
                return "잘못된 요청입니다. 다시 확인해 주세요"
        }else{
            return "잘못된 정보가 기입되어 있습니다."
        }
    }
//    @Transactional
//    fun loginmember(memberid: String ,memberpw: String) : String {
//        val authenticationToken = membernamePasswordAuthenticationToken(memberid, memberpw);
//        singServie.loadmemberBymembername(memberid);
//
//    }
}

