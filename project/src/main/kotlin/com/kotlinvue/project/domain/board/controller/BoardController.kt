package com.kotlinvue.project.domain.board.controller

import com.kotlinvue.project.domain.board.dto.BoardRequest
import com.kotlinvue.project.domain.board.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController {
    @Autowired
    private lateinit var boardService: BoardService
//    @GetMapping("/GetboardList")
//    fun GetBoardList(model: Model) : String {
//
//    }

    @PostMapping("/CreateBoard")
    fun CreateBoard(@RequestBody boardRequest : BoardRequest ) : ResponseEntity<Any> {
        boardService.createBoard(boardRequest)
        return ResponseEntity
                .ok()
                .body(true)
    }
}