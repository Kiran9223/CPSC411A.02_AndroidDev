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

    @POST("notes")
    suspend fun createNote(@Body note: NoteRequest): Note

    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") noteId: String, @Body note: NoteRequest): Note

    @GET("notes")
    suspend fun searchNotes(
        @Query("title") title: String? = null,
        @Query("body") body: String? = null
    ): List<Note>


}