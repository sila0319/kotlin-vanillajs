package com.example.practice.domain.user.controller

import com.example.practice.domain.user.service.UserService
import com.example.practice.domain.user.dto.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller


import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/user")
class UserController (
    @Autowired
    private val userService : UserService,
){
    //@RequestBody useReq: UserReq
    @PostMapping("/login")
    fun login(m : ModelAttribute) : String{

        println("로그인 시도")
        return ""
    }
    @GetMapping("login")
    fun login() : String{
        //로그인 페이지 이동
        return "views/user/login"
    }

    @GetMapping("/logout")
    fun logout(){
       println("로그아웃 시도")
    }

    @GetMapping("/createUser")
    fun signup() : String{
        return "views/user/create"
    }

    @PostMapping("/createUser")
    fun createUser(userSaveReq: UserSaveReq, redirectAttributes : RedirectAttributes) : String{
        var msg : String = userService.createUser(userSaveReq)+"님 환영합니다."

        redirectAttributes.addAttribute("msg" , msg)

        return "redirect:/"
    }

    @GetMapping("/getAllUser") //관리자 페이지로 뺄 가능성 있음
    fun getAllUser(@PageableDefault(size = 10) pageable: Pageable) : ResponseEntity<Page<UserRes>> {
        var userList : Page<UserRes> = userService.getAllUser(pageable)
        return ResponseEntity(userList , HttpStatus.OK)
    }

    @GetMapping("/getUser/{id}")
    fun getUser(@PathVariable id : Long) : ResponseEntity<UserRes?>{
        var user : UserRes? = userService.getUser(id)
        return ResponseEntity(user, HttpStatus.OK)
    }
    @PutMapping("/updateUser")
    fun updateUser (@ModelAttribute userSaveReq: UserSaveReq) : ResponseEntity<UserSaveRes>{
        var userSaveRes: UserSaveRes =  userService.updateUser(userSaveReq)
        return ResponseEntity(userSaveRes, HttpStatus.OK)
    }

    @DeleteMapping("/deleteUser/{id}")
    fun deleteUser (@PathVariable id : Long , @ModelAttribute userDeleteReq: UserDeleteReq ) : ResponseEntity<String>{
        var message : String = userService.deleteUser(id , userDeleteReq)
        return ResponseEntity(message, HttpStatus.OK)
    }


}