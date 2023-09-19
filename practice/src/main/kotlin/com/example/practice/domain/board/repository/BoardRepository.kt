package com.example.practice.domain.board.repository

import com.example.practice.domain.board.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {

}