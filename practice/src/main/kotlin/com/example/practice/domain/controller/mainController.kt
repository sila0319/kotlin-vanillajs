package com.example.practice.domain.controller

import jakarta.servlet.http.HttpSession
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

import java.security.Principal




@Controller
class mainController (
    private val authenticationManagerBuilder : AuthenticationManagerBuilder
){
    @GetMapping("/")
    fun main():String {
        return "views/home.html"
    }
}