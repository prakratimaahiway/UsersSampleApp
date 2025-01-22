package com.maahiway.userssampleapp.data.repository

import com.maahiway.userssampleapp.data.database.UserDao
import com.maahiway.userssampleapp.data.model.User
import com.maahiway.userssampleapp.data.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun getUser(): List<User> {
        return apiService.getUser()
    }

    suspend fun getUserById(id: Int): User {
        return apiService.getUserById(id)
    }
}