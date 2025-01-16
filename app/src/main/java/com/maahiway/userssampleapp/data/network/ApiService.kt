package com.maahiway.userssampleapp.data.network

import com.maahiway.userssampleapp.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //https://jsonplaceholder.typicode.com/users
    @GET("users")
    suspend fun getUser(): List<User>

    //https://jsonplaceholder.typicode.com/users/1
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}