package com.mypractical.ui.employee

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mypractical.data.Employee
import com.mypractical.databinding.ItemEmployeeLayoutBinding

class EmployeeAdapter(private var employeeList: List<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemEmployeeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            binding.employeeData = employee
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEmployeeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = employeeList[position]
        holder.binding.tvEpName.text = data.employee_name
        holder.binding.tvEpAge.text = data.employee_age.toString()
        holder.bind(employee = data)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newEmployees: List<Employee>) {
        employeeList = newEmployees
        notifyDataSetChanged()
    }

}