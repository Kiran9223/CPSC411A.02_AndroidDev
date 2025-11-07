package com.example.jetpackcomposebasicsdemo.notesapp

data class NoteRequest(
    val title: String,
    val body: String,
    val userId: String = "1"
)
