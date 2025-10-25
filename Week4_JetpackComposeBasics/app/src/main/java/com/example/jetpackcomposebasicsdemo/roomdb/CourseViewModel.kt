package com.example.jetpackcomposebasicsdemo.roomdb

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CourseViewModel(context: Context): ViewModel() {

    private val dao: CourseDao = createDatabase(context).courseDao()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            dao.getAllCourses().collect() {
                courseList -> _courses.value = courseList
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            dao.updateCourse(course.copy(isFavorite = !course.isFavorite))
        }
    }

}