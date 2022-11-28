package br.com.alura.forum.data.mapper

import br.com.alura.forum.data.model.AnswerRequest
import br.com.alura.forum.domain.model.Answer
import br.com.alura.forum.domain.service.TopicService
import br.com.alura.forum.domain.service.UserService
import org.springframework.stereotype.Component

@Component
class AnswerRequestMapper(
    private val userService: UserService,
    private val topicService: TopicService,
): Mapper<AnswerRequest, Answer> {

    override fun map(t: AnswerRequest): Answer {
        return Answer(
            message = t.message,
            author = userService.getAuthorById(t.authorId),
            topic = topicService.getTopicById(t.topicId),
            isSolution = t.isSolution
        )
    }
}