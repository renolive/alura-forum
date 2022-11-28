package br.com.alura.forum.domain.model

import java.time.LocalDateTime

data class Answer(
    val id: Long? = null,
    val message: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val isSolution: Boolean
)
