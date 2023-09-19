package com.example.practice.domain.post.entity

import com.example.practice.domain.board.entity.Board
import com.example.practice.domain.file.entity.File
import com.example.practice.domain.user.entity.User
import com.example.practice.global.entity.AuditingEntity
import jakarta.persistence.*
import org.apache.commons.lang3.mutable.Mutable

@Entity
@Table(name = "posts")
class Post (
    title : String,
    content : String,
    views : Long,
    user : User,
    board : Board,
    @OneToMany(mappedBy = "post")
    var files : MutableList<File>


): AuditingEntity() {
    @Column(nullable = false)
    var title : String = title
        protected set
    @Column(nullable = false)
    var content : String = content
        protected  set
    @Column(nullable = false)
    var views : Long = views
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    var user : User = user
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    var board : Board = board
        protected set


}