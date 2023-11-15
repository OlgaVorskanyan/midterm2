package com.example.midterm
import retrofit2.http.GET

import User

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
}