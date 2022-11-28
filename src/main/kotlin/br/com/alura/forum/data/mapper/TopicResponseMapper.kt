package br.com.alura.forum.data.mapper

import br.com.alura.forum.data.model.TopicResponse
import br.com.alura.forum.domain.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicResponseMapper: Mapper<Topic, TopicResponse> {

    override fun map(t: Topic): TopicResponse {
        return TopicResponse(
            t.id,
            t.title,
            t.message,
            t.status,
            t.date
        )

    }
}