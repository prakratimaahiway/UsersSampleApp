package com.maahiway.userssampleapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(@PrimaryKey val id: Int,
                         val street: String,
                         val city: String,
                         val zipcode: String)