package com.maahiway.userssampleapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maahiway.userssampleapp.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<UserEntity>>
}