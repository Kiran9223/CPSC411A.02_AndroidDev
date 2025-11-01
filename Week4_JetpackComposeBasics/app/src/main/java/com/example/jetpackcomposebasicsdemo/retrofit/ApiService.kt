package com.example.jetpackcomposebasicsdemo.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

}