package com.itis.taskmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.time.LocalDateTime
import java.util.EnumSet.range

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Tasks"
val COL_ID = "id"
val COL_NAME = "name"
val COL_SHORT_DESC = "short_desc"
val COL_DESC = "desc"
val COL_DEADLINE = "deadline"

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    var counter = 0
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_NAME + " VARCHAR(256)," +
                COL_SHORT_DESC + " VARCHAR(256)," +
                COL_DESC + " VARCHAR(256)," +
                COL_DEADLINE + " VARCHAR(256)" +
        ")"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(task: Task) {
        val db = this.writableDatabase

        val dl = task.deadline
        var deadlinetext = dl.year.toString() + "_" +
                dl.month.value.toString() + "_" +
                dl.dayOfMonth.toString() + "_" +
                dl.hour.toString() + "_" +
                dl.minute.toString()

        var cv = ContentValues()
        cv.put(COL_ID, task.id)
        cv.put(COL_NAME, task.name)
        cv.put(COL_SHORT_DESC, task.short_desc)
        cv.put(COL_DESC, task.desc)
        cv.put(COL_DEADLINE, deadlinetext)

        db.insert(TABLE_NAME, null, cv)
    }

    fun readData() {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                counter++
                var id = result.getString(0).toInt()
                var name = result.getString(1)
                var short_desc = result.getString(2)
                var desc = result.getString(3)
                var deadlinetext = result.getString(4)
                var dls = deadlinetext.split("_")
                var deadline = LocalDateTime.of(dls[0].toInt(), dls[1].toInt(), dls[2].toInt(), dls[3].toInt(), dls[4].toInt())

                var task = Task(id, name, short_desc, desc, deadline)
                TaskRepository.list.add(task)
            } while (result.moveToNext())
        }
    }

    fun deleteData(id: Int) {
        val query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + id + ";"
        var db = this.writableDatabase

        db.execSQL(query)
    }

    fun editData(task: Task) {
        val deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + task.id + ";"
        var db = this.writableDatabase
        db.execSQL(deleteQuery)
        insertData(task)

    }

    fun clearTable() {
        var db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME + ';')
        db.execSQL("VACUUM;")
    }
}