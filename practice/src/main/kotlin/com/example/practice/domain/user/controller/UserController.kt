package com.example.practice.domain.user.controller

import com.example.practice.domain.user.service.UserService
import com.example.practice.domain.user.dto.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity




import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/v1/user")
class UserController (
    @Autowired
    private val userService : UserService,
){
    //@RequestBody useReq: UserReq
    @PostMapping("/login")
    fun login(){

        println("로그인 시도")
    }

    @GetMapping("/logout")
    fun logout(){
       println("로그아웃 시도")
    }

    @PostMapping("/createUser")
    fun createUser(@RequestBody userSaveReq: UserSaveReq) : ResponseEntity<UserSaveRes> {
       var userSaveRes : UserSaveRes? = userService.createUser(userSaveReq)
        return ResponseEntity(userSaveRes, HttpStatus.OK)
    }

    @GetMapping("/getAllUser")
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
    fun updateUser (@RequestBody userSaveReq: UserSaveReq) : ResponseEntity<UserSaveRes>{
        var userSaveRes: UserSaveRes =  userService.updateUser(userSaveReq)
        return ResponseEntity(userSaveRes, HttpStatus.OK)
    }

    @DeleteMapping("/deleteUser/{id}")
    fun deleteUser (@PathVariable id : Long , @RequestBody userDeleteReq: UserDeleteReq ) : ResponseEntity<String>{
        var message : String = userService.deleteUser(id , userDeleteReq)
        return ResponseEntity(message, HttpStatus.OK)
    }


}