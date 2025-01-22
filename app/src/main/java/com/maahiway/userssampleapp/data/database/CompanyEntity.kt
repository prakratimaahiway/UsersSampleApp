package com.maahiway.userssampleapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class CompanyEntity(@PrimaryKey val id: Int,
                         val name: String,
                         val catchPhrase: String)