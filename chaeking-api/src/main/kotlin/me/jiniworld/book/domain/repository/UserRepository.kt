package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<User, Long>