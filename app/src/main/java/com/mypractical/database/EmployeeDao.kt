package com.mypractical.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mypractical.data.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<Employee>)

    @Query("SELECT * FROM employee WHERE company_id = :companyId")
    suspend fun getEmployeesForCompany(companyId: Int): List<Employee>
}