package com.example.jetpackcomposebasicsdemo.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Course(
//    val id: String,
//    val name: String,
//    val lessons: List<String>
//)

//val courses = listOf(
//    Course("java", "Java Basics",
//            listOf("Variables", "Functions", "Data types", "Classes", "Inheritance")
//        ),
//    Course("kotlin", "Kotlin Basics",
//        listOf("Variables", "Functions", "Data types", "Classes", "Inheritance")
//    )
//
//)


//fun getCourse(id: String) = courses.find {it.id == id}



@Entity(tableName = "coursesTable")
data class Course(
    @PrimaryKey
    val id: String,
    val name: String,
    val isFavorite: Boolean = false
)


























