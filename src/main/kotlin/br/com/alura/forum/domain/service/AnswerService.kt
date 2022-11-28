package br.com.alura.forum.domain.service

import br.com.alura.forum.data.mapper.AnswerRequestMapper
import br.com.alura.forum.data.mapper.AnswerResponseMapper
import br.com.alura.forum.data.model.AnswerRequest
import br.com.alura.forum.data.model.AnswerResponse
import br.com.alura.forum.data.model.AnswerUpdateRequest
import br.com.alura.forum.domain.model.Answer
import br.com.alura.forum.domain.model.Course
import br.com.alura.forum.domain.model.Topic
import br.com.alura.forum.domain.model.User
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answers: MutableList<Answer> = mutableListOf(),
    private val answerRequestMapper: AnswerRequestMapper,
    private val answerResponseMapper: AnswerResponseMapper
) {

    init {
        answers.add(
            Answer(
                id = 1,
                message = "Default Message",
                author = User(
                    1L,
                    "Default User",
                    email = "default@email.com"
                ),
                topic = Topic(
                    id = 1L,
                    title = "Default",
                    message = "Default",
                    course = Course(
                        1L,
                        "Default Course",
                        "Default Category"
                    ),
                    author = User(
                        1L,
                        "Default User",
                        email = "default@email.com"
                    )
                ),
                isSolution = false
            )
        )
    }

    fun getTopicAnswers(id: Long): List<AnswerResponse> =
        answers.filter { it.topic.id == id }
            .map { answerResponseMapper.map(it) }

    fun registerAnswer(answerRequest: AnswerRequest): AnswerResponse {
        val newAnswer = answerRequestMapper.map(answerRequest).copy(id = answers.size + 1L)
        answers.add(newAnswer)
        return answerResponseMapper.map(newAnswer)
    }

    fun updateAnswer(id: Long, answerUpdateRequest: AnswerUpdateRequest): AnswerResponse {
        val answerIndex = answers.indexOfFirst {
            it.id == id
        }

        answers[answerIndex] = answers[answerIndex].copy(
            message = answerUpdateRequest.message,
            isSolution = answerUpdateRequest.isSolution
        )

        return answerResponseMapper.map(answers[answerIndex])
    }

    fun deleteAnswer(id: Long) {
        answers.removeIf { it.id == id }
    }
}