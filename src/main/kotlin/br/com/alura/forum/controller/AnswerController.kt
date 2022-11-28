package br.com.alura.forum.controller

import br.com.alura.forum.data.model.AnswerRequest
import br.com.alura.forum.data.model.AnswerResponse
import br.com.alura.forum.data.model.AnswerUpdateRequest
import br.com.alura.forum.domain.service.AnswerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.function.EntityResponse
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos/{id}/respostas")
class AnswerController(private val answerService: AnswerService) {

    @GetMapping
    fun getTopicAnswers(@PathVariable id: Long): List<AnswerResponse> =
        answerService.getTopicAnswers(id)

    @PostMapping
    fun registerAnswer(
        @RequestBody @Valid answerRequest: AnswerRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AnswerResponse> {
        val newAnswer = answerService.registerAnswer(answerRequest)
        val uri = uriBuilder.path("/topicos/${answerRequest.topicId}/respostas/${newAnswer.id}").build().toUri()
        return ResponseEntity.created(uri).body(newAnswer)
    }

    @PutMapping("/{answerId}")
    fun updateAnswer(
        @PathVariable answerId: Long,
        @RequestBody @Valid answerUpdateRequest: AnswerUpdateRequest
    ): ResponseEntity<AnswerResponse> {
        val updatedAnswer = answerService.updateAnswer(answerId, answerUpdateRequest)
        return ResponseEntity.ok(updatedAnswer)
    }

    @DeleteMapping("/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAnswer(
        @PathVariable answerId: Long
    ) {
        answerService.deleteAnswer(answerId)
    }
}