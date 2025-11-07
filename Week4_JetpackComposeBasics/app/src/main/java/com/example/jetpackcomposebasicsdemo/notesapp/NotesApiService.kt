package com.example.jetpackcomposebasicsdemo.notesapp

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NotesApiService {
    @GET("notes")
    suspend fun getAllNotes(): List<Note>


}