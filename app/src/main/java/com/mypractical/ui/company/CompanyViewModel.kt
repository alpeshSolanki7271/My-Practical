package com.mypractical.ui.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mypractical.data.Company
import com.mypractical.repository.AppRepository
import com.mypractical.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _companies = MutableLiveData<List<Company>>()
    val companies: LiveData<List<Company>> get() = _companies

    init {
        addCompanies()
    }

    private fun addCompanies() {

        val gson = Gson()
        val companyType = object : TypeToken<List<Company>>() {}.type
        val companies: List<Company> = gson.fromJson(AppConstants.companyJson, companyType)

        viewModelScope.launch {
            appRepository.insertCompanies(companies = companies)
        }
    }

    fun fetchCompanies() {
        viewModelScope.launch {
            _companies.value = appRepository.getAllCompanies()
        }
    }
}