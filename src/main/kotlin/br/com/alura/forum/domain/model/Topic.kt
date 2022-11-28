package br.com.alura.forum.domain.model

import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    val answers: List<Answer> = ArrayList()
)