package com.mypractical.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(
    @PrimaryKey val id: Int,
    val company_name: String
)