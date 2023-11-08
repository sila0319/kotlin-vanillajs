package com.example.practice.domain.member.controller

import com.example.practice.domain.member.service.MemberService
import com.example.practice.domain.member.dto.*
import com.example.practice.domain.member.service.SignService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller


import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/member")
class MemberController (
    @Autowired
    private val memberService : MemberService,
        private val signService: SignService,
){
    //@RequestBody membereq: memberReq
//    @PostMapping("/login")
//    fun login(m : ModelAttribute) : String{
//
//        println("로그인 시도")
//        return ""
//    }

//    @PostMapping("/login")
//    fun postLogin(memberLoginReq: MemberLoginReq ) : String{
//        signService.authenticateUser(memberLoginReq.memberid, memberLoginReq.memberpw)
//        return ""
//    }


    @GetMapping("/login")
    fun login() : String{
        //로그인 페이지 이동
        println("로그인 Get")
        return "views/member/login"
    }

    @GetMapping("/logout")
    fun logout(){
       println("로그아웃 시도")
    }

    @GetMapping("/createMember")
    fun signup() : String{
        println("유저 생성 Get")
        return "views/member/create"
    }

    @PostMapping("/createMember")
    fun createmember(memberSaveReq: MemberSaveReq) : String{
        println("유저 생성 요청")
        var memberSaveRes : MemberSaveRes =  memberService.createmember(memberSaveReq)
        return "views/member/login"
    }

    @GetMapping("/getAllMember") //관리자 페이지로 뺄 가능성 있음
    fun getAllmember(@PageableDefault(size = 10) pageable: Pageable) : ResponseEntity<Page<MemberRes>> {
        var memberList : Page<MemberRes> = memberService.getAllmember(pageable)
        return ResponseEntity(memberList , HttpStatus.OK)
    }

    @GetMapping("/getMember/{id}")
    fun getmember(@PathVariable id : Long) : ResponseEntity<MemberRes?>{
        var member : MemberRes? = memberService.getmember(id)
        return ResponseEntity(member, HttpStatus.OK)
    }
    @PutMapping("/updateMember")
    fun updatemember (@ModelAttribute memberSaveReq: MemberSaveReq) : ResponseEntity<MemberSaveRes>{
        var memberSaveRes: MemberSaveRes =  memberService.updatemember(memberSaveReq)
        return ResponseEntity(memberSaveRes, HttpStatus.OK)
    }

    @DeleteMapping("/deleteMember/{id}")
    fun deletemember (@PathVariable id : Long , @ModelAttribute memberDeleteReq: MemberDeleteReq ) : ResponseEntity<String>{
        var message : String = memberService.deletemember(id , memberDeleteReq)
        return ResponseEntity(message, HttpStatus.OK)
    }


}