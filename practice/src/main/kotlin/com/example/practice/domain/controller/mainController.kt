package com.example.practice.domain.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class mainController (

){
    @GetMapping("/")
    fun main():String {
        return "views/home.html"
    }
}