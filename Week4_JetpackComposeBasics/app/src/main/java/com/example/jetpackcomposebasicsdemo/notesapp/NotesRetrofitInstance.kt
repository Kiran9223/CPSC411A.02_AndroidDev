package com.example.jetpackcomposebasicsdemo.notesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotesRetrofitInstance {
    private const val BASE_URL = "https://690993b82d902d0651b44545.mockapi.io/api/v1/"

    val api: NotesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotesApiService::class.java)
    }
}