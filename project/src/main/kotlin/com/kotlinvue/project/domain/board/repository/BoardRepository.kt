package com.kotlinvue.project.domain.board.repository

import org.springframework.data.repository.CrudRepository
import  com.kotlinvue.project.domain.board.entity.Board

interface BoardRepository : CrudRepository<Board, Long> {
}