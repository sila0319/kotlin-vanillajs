package com.kotlinvue.project.domain.board.dto

import com.kotlinvue.project.domain.board.entity.Board
import java.time.OffsetDateTime

data class BoardRequest(
        val id : Long? = null,
        val title : String? = null,
        val content : String? = null ,
        val category : String

        ){
    fun toEntity() : Board {
        return Board(
                id = id,
                title = title,
                content = content,
                category = category
        )
    }
}

data class BoardResponse(
        val id : Long? = null,
        val title : String? = null,
        val content : String? = null,
        val category : String,

    ){
    fun toEntity() : Board {
        return Board(
                id = id,
                title = title,
                content = content,
                category = category
        )
    }
}