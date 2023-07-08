package com.itis.taskmanager

import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Serializable
object TaskRepository {

    var list: MutableList<Task> = mutableListOf(
    )
}