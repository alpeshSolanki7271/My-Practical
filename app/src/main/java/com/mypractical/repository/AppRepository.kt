package com.mypractical.repository

import com.mypractical.data.Company
import com.mypractical.data.CompanyWithEmployees
import com.mypractical.data.Employee
import com.mypractical.database.CompanyDao
import com.mypractical.database.EmployeeDao
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val companyDao: CompanyDao,
    private val employeeDao: EmployeeDao
) {

    suspend fun insertCompanies(companies: List<Company>) {
        companyDao.insertAll(companies)
    }

    suspend fun insertEmployees(employees: List<Employee>) {
        employeeDao.insertAll(employees)
    }

    suspend fun getAllCompanies(): List<Company> {
        return companyDao.getAllCompanies()
    }

    suspend fun getEmployeesForCompany(companyId: Int): List<Employee> {
        return employeeDao.getEmployeesForCompany(companyId)
    }

    suspend fun getCompanyWithEmployees(companyId: Int): CompanyWithEmployees {
        return companyDao.getCompanyWithEmployees(companyId)
    }

}