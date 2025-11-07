package com.example.jetpackcomposebasicsdemo.notesapp

data class Note(
    val id: String = "0",
    val title: String,
    val body: String,
    val userId: String = "1"
)
