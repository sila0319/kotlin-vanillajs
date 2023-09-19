package com.example.practice.domain.board.entity

import com.example.practice.domain.post.entity.Post
import com.example.practice.global.entity.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "boards")
class Board (
    boardname : String,
    @OneToMany(mappedBy = "board")
    val posts : MutableList<Post>

) : AuditingEntity(){

    @Column(nullable = false)
    var boardname = boardname
        protected  set


}