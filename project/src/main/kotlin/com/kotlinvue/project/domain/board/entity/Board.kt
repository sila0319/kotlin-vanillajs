package com.kotlinvue.project.domain.board.entity

import com.kotlinvue.project.domain.board.dto.BoardRequest
import com.kotlinvue.project.domain.board.dto.BoardResponse
import jakarta.persistence.*
import java.time.OffsetDateTime


@Entity
class Board(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long? = null,
        var title : String? = null,
        var content : String? = null,
        @Enumerated(EnumType.STRING)
        var category: String,
        val createDateTime : OffsetDateTime = OffsetDateTime.now(),
        var updateDateTime : OffsetDateTime? = null
) {
    fun toBoardResponse(): BoardResponse{
        return BoardResponse(
                id = id,
                title = title,
                content = content,
                category = category

        )
    }

    fun toBoardRequest (): BoardRequest{
        return BoardRequest(
                id = id,
                title = title,
                content = content,
                category = category
        )
    }
}
