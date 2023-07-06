package com.itis.taskmanager

import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.time.LocalDate
@Serializable
object TaskRepository {

    var list: List<Task> = mutableListOf(
        Task(0, "test", "short_description", "long description", "10/7/2023"),
        Task(0, "test", "short_description", "long description", "10/7/2023"),
        Task(0, "test", "short_description", "long description", "10/7/2023"),
        Task(0, "test", "short_description", "long description", "10/7/2023"),
        Task(0, "test", "short_description", "long description", "10/7/2023"),
        Task(0, "test", "short_description", "long description", "10/7/2023")
    )
}