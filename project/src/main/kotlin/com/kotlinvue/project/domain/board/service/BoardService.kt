package com.kotlinvue.project.domain.board.service

import com.kotlinvue.project.domain.board.dto.BoardRequest
import com.kotlinvue.project.domain.board.dto.BoardResponse
import com.kotlinvue.project.domain.board.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired

class BoardService {

    @Autowired
    lateinit var boardRepository : BoardRepository

    fun readBoard(): List<BoardResponse> {
        val board = boardRepository.findAll()

        return board.map{ it.toBoardResponse() }
    }

    @Transactional
    fun createBoard(boardRequest: BoardRequest) : BoardRequest{
        val board = boardRepository.save(boardRequest.toEntity())
        return board.toBoardRequest()

    }
}