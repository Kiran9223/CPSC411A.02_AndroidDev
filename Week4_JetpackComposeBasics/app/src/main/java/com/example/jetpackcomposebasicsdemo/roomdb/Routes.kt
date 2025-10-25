package com.example.jetpackcomposebasicsdemo.roomdb

object Routes {
    const val HOME = "home"
    const val COURSE = "course/{courseId}"
    const val LESSON = "course/{courseId}/lesson/{lessonId}"

    fun course(courseId: String) = "course/$courseId"

    fun lesson(courseId: String, lessonId: Int) = "course/$courseId/lesson/$lessonId"
}
