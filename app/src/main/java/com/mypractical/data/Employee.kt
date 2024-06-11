package com.mypractical.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "employee", foreignKeys = [ForeignKey(
        entity = Company::class,
        parentColumns = ["id"],
        childColumns = ["company_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Employee(
    @PrimaryKey val id: Int,
    val employee_name: String,
    val employee_salary: Int,
    val employee_age: Int,
    val company_id: Int,
    val profile_image: String
)