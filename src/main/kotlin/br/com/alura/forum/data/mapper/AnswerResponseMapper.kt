package br.com.alura.forum.data.mapper

import br.com.alura.forum.data.model.AnswerResponse
import br.com.alura.forum.domain.model.Answer
import org.springframework.stereotype.Component

@Component
class AnswerResponseMapper : Mapper<Answer, AnswerResponse> {

    override fun map(t: Answer): AnswerResponse {
        return AnswerResponse(
            id = t.id,
            message = t.message,
            isSolution = t.isSolution,
            date = t.date
        )
    }
}
