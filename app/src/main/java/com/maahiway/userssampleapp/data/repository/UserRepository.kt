package com.maahiway.userssampleapp.data.repository

import com.maahiway.userssampleapp.data.model.User
import com.maahiway.userssampleapp.data.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUser(): List<User> {
        return apiService.getUser()
    }

    suspend fun getUserById(id: Int): User {
        return apiService.getUserById(id)
    }
}