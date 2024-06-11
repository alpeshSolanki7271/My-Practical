package com.mypractical.data

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithEmployees(
    @Embedded val company: Company,
    @Relation(
        parentColumn = "id",
        entityColumn = "company_id"
    )
    val employees: List<Employee>
)