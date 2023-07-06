package com.itis.taskmanager

import android.util.JsonWriter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.io.FileWriter

class HandlerJSON() {
    val json = Json.encodeToJsonElement(TaskRepository)

    fun test() {
        var wr = FileWriter("test.txt")
        wr.write(json.toString())
    }
}