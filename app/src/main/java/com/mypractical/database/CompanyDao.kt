package com.mypractical.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mypractical.data.Company
import com.mypractical.data.CompanyWithEmployees

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(companies: List<Company>)

    @Query("SELECT * FROM company")
    suspend fun getAllCompanies(): List<Company>

    @Transaction
    @Query("SELECT * FROM company WHERE id = :companyId")
    suspend fun getCompanyWithEmployees(companyId: Int): CompanyWithEmployees
}