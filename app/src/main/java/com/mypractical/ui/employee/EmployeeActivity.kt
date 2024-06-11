package com.mypractical.ui.employee

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mypractical.R
import com.mypractical.databinding.ActivityEmployeeBinding
import com.mypractical.util.AppConstants.Companion.COMPANY_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        val companyId = intent.getIntExtra(COMPANY_ID, 0)

        val adapter = EmployeeAdapter(emptyList())
        binding.employeeRv.adapter = adapter

        viewModel.fetchEmployeesForCompany(companyId)
        viewModel.employees.observe(this) { employees ->
            adapter.updateData(employees)
        }


    }
}