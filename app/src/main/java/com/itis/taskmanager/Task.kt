package com.itis.taskmanager

import java.text.DateFormat
import java.time.LocalDate

data class Task(
    var id: Int,
    var name: String,
    var short_desc: String,
    var desc: String,
    var deadline: String
)