package br.com.alura.forum.data.model

import java.time.LocalDateTime

data class AnswerResponse(
    val id: Long?,
    val message: String,
    val isSolution: Boolean,
    val date: LocalDateTime
)