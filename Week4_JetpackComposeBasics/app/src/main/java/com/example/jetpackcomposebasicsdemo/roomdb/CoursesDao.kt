package com.example.jetpackcomposebasicsdemo.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM coursesTable")
    fun getAllCourses(): Flow<List<Course>>

    @Insert()
    suspend fun insertCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)



}