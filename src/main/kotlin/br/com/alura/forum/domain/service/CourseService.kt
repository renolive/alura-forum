package br.com.alura.forum.domain.service

import br.com.alura.forum.domain.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(private val courses: MutableList<Course> = mutableListOf()) {

    init {
        courses.add(
            Course(
                1L,
                "Default Course",
                "Default Category"
            )
        )
    }

    fun getCourseById(id: Long): Course {
        return courses.firstOrNull { it.id == id } ?: courses.first()
    }
}
