package com.example.jetpackcomposebasicsdemo.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Course::class],
    version = 1
)
abstract class CoursesDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}

fun createDatabase(context: Context): CoursesDatabase {
    return Room.databaseBuilder(
        context,
        CoursesDatabase::class.java,
        "courses_db"
    ).build()
}