package com.example.practice.global.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode (
    val code : String,
    val message : String,

){
    /**CustomException 생성자 전용 코드 */
    CUSTOM_ERROR(code = "C000" , message = "custom_error"),

    /**잘못된 값이 들어 왔을때 사용하는 에러코드 */
    INVALID_INPUT_VALUE(code = "C001" , message = "invalid input value"),
    /**엔티티를 못찾을 경우 사용하는 에러코드 */
    ENTITY_NOT_FOUND(code = "C002" , message = "Entity not found")


}