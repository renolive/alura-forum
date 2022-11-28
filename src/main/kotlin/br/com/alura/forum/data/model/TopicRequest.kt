package br.com.alura.forum.data.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class TopicRequest(
    @get:NotEmpty @get:Size(min = 5, max = 100) val title: String,
    @get:NotEmpty val message: String,
    @get:NotNull val courseId: Long,
    @get:NotNull val authorId: Long
)
