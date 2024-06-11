package com.mypractical.ui.company

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mypractical.R
import com.mypractical.databinding.ActivityMainBinding
import com.mypractical.ui.employee.EmployeeActivity
import com.mypractical.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CompanyViewModel
    private lateinit var adapter: CompanyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[CompanyViewModel::class.java]

        adapter = CompanyAdapter(emptyList()) { companyId ->
            val intent = Intent(this@MainActivity, EmployeeActivity::class.java)
            intent.putExtra(AppConstants.COMPANY_ID, companyId)
            startActivity(intent)
        }
        binding.companyRv.adapter = adapter

        viewModel.fetchCompanies()
        viewModel.companies.observe(this) { companies ->
            adapter.updateData(companies)
        }

    }
}