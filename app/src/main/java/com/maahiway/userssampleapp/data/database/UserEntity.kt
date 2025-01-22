package com.maahiway.userssampleapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maahiway.userssampleapp.data.model.Address
import com.maahiway.userssampleapp.data.model.Company

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey  val id: Int,
                      val name: String,
                      val email: String,
                      val addressId: Int,
                      val companyId: Int)