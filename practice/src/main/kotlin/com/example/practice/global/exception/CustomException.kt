package com.example.practice.global.exception

sealed class CustomException(message : String?) : BusinessException(message , ErrorCode.CUSTOM_ERROR) {

}

class EntityNofFoundException(Entity: String?) : CustomException("$Entity not found Exception")

class UserNotFoundException (id: Long?) : CustomException("$id not found Exception")

