package br.com.alura.forum.data.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AnswerRequest(
    @get:NotEmpty @get:Size(min = 5, max = 300) val message: String,
    @get:NotNull val authorId: Long,
    @get:NotNull val topicId: Long,
    @get:NotNull val isSolution: Boolean
)
