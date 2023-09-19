package com.example.practice.global.exception

open class BusinessException  : RuntimeException{
    constructor(message: String?, customError: ErrorCode) : super(message)
}