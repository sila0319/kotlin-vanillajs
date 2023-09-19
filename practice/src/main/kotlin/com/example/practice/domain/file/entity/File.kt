package com.example.practice.domain.file.entity

import com.example.practice.domain.post.entity.Post
import com.example.practice.global.entity.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "files")
class File(
    filepath : String,
    filename : String,
    filesize : Long,
    post : Post,

    ): AuditingEntity() {
    @Column(nullable = false)
    var filepath = filepath
        protected  set

    @Column(nullable = false)
    var filename = filename
        protected  set
    @Column(nullable = false)
    var filesize = filesize
        protected  set
    @ManyToOne
    @JoinColumn(name="post_id")
    var post : Post = post
        protected set

}