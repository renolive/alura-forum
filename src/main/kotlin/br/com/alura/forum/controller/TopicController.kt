package br.com.alura.forum.controller

import br.com.alura.forum.data.model.TopicRequest
import br.com.alura.forum.data.model.TopicResponse
import br.com.alura.forum.data.model.TopicUpdateRequest
import br.com.alura.forum.domain.service.TopicService
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun getTopicList(): List<TopicResponse> = topicService.getTopicList()

    @GetMapping("/{id}")
    fun getTopicById(@PathVariable id: Long): TopicResponse {
        return topicService.getTopicResponseById(id)
    }

    @PostMapping
    fun registerTopic(
        @RequestBody @Valid topicRequest: TopicRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicResponse> {
        val newTopic = topicService.registerTopic(topicRequest)
        val uri = uriBuilder.path("/topicos/${newTopic.id}").build().toUri()
        return ResponseEntity.created(uri).body(newTopic)
    }

    @PutMapping
    fun updateTopic(
        @RequestBody @Valid topic: TopicUpdateRequest,
    ): ResponseEntity<TopicResponse> {
        val topicResponse = topicService.updateTopic(topic)
        return ResponseEntity.ok(topicResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopic(@PathVariable id: Long) {
        topicService.deleteTopic(id)
    }
}