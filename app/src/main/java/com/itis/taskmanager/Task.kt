package com.itis.taskmanager

import java.lang.reflect.Constructor
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class Task(
    var id: Int,
    var name: String,
    var short_desc: String,
    var desc: String,
    var deadline: LocalDateTime

    )