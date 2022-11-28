package br.com.alura.forum.domain.service

import br.com.alura.forum.data.mapper.TopicRequestMapper
import br.com.alura.forum.data.mapper.TopicResponseMapper
import br.com.alura.forum.data.model.TopicRequest
import br.com.alura.forum.data.model.TopicResponse
import br.com.alura.forum.data.model.TopicUpdateRequest
import br.com.alura.forum.domain.model.Course
import br.com.alura.forum.domain.model.Topic
import br.com.alura.forum.domain.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topics: MutableList<Topic> = mutableListOf(),
    private val topicResponseMapper: TopicResponseMapper,
    private val topicRequestMapper: TopicRequestMapper
) {

    init {
        topics.add(
            Topic(
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
            )
        )
    }

    fun getTopicList(): List<TopicResponse> {
        return topics.map { topicResponseMapper.map(it) }
    }

    fun getTopicResponseById(id: Long): TopicResponse {
        return (topics.firstOrNull { it.id == id } ?: topics.first())
            .run { topicResponseMapper.map(this) }
    }

    fun getTopicById(id: Long): Topic {
        return topics.firstOrNull { it.id == id } ?: topics.first()
    }

    fun registerTopic(topicRequest: TopicRequest): TopicResponse {
        val newTopic = topicRequestMapper.map(topicRequest).copy(id = topics.size + 1L)
        topics.add(newTopic)
        return topicResponseMapper.map(newTopic)
    }

    fun updateTopic(updatedTopic: TopicUpdateRequest): TopicResponse {
        val topicIndex = topics.indexOfFirst { it.id == updatedTopic.id }
        topics[topicIndex] = topics[topicIndex].copy(
            title = updatedTopic.title,
            message = updatedTopic.message
        )
        return topicResponseMapper.map(topics[topicIndex])
    }

    fun deleteTopic(id: Long) {
        topics.removeIf { it.id == id }
    }
}