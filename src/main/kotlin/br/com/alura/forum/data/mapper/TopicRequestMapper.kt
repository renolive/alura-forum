package br.com.alura.forum.data.mapper

import br.com.alura.forum.domain.model.Topic
import br.com.alura.forum.data.model.TopicRequest
import br.com.alura.forum.domain.service.CourseService
import br.com.alura.forum.domain.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicRequestMapper(
    private val courseService: CourseService,
    private val userService: UserService
) : Mapper<TopicRequest, Topic> {
    override fun map(t: TopicRequest): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.getCourseById(t.courseId),
            author = userService.getAuthorById(t.authorId)
        )
    }
}