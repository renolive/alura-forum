package br.com.alura.forum.domain.service

import br.com.alura.forum.domain.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val users: MutableList<User> = mutableListOf()) {

    init {
        users.add(
            User(
                1L,
                "Default User",
                email = "default@email.com"
            )
        )
    }

    fun getAuthorById(id: Long) =
        users.firstOrNull { it.id == id } ?: users.first()
}
