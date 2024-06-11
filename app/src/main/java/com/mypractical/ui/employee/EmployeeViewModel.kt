package com.mypractical.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mypractical.data.Employee
import com.mypractical.repository.AppRepository
import com.mypractical.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(val appRepository: AppRepository) : ViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> get() = _employees

    init {
        addEmployees()
    }

    private fun addEmployees() {
        val gson = Gson()

        val employeeType = object : TypeToken<List<Employee>>() {}.type
        val employees: List<Employee> = gson.fromJson(AppConstants.employeeJson, employeeType)

      /*  val employeeType = object : TypeToken<Map<String, Any>>() {}.type
        val employeeData: Map<String, Any> = gson.fromJson(employeeJson, employeeType)
        val employees: List<Employee> = gson.fromJson(gson.toJson(employeeData["data"]), employeeType)*/

        viewModelScope.launch {
            appRepository.insertEmployees(employees = employees)
        }
    }


    fun fetchEmployeesForCompany(companyId: Int) {
        viewModelScope.launch {
            _employees.value = appRepository.getCompanyWithEmployees(companyId).employees
        }
    }
}