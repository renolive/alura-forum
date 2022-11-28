package br.com.alura.forum.data.model

import br.com.alura.forum.domain.model.StatusTopic
import java.time.LocalDateTime

data class TopicResponse(
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val date: LocalDateTime
)
