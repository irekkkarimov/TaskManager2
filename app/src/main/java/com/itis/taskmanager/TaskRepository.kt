package com.itis.taskmanager

import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Serializable
object TaskRepository {

    var list: MutableList<Task> = mutableListOf(
        Task(1, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0)),
        Task(2, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0)),
        Task(3, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0)),
        Task(4, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0)),
        Task(5, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0)),
        Task(6, "test", "short_description", "long description", LocalDateTime.of(2023, 7, 8, 18, 0))
    )
}