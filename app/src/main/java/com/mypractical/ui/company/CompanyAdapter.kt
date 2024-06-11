package com.mypractical.ui.company

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mypractical.data.Company
import com.mypractical.databinding.ItemCompanyLayoutBinding

class CompanyAdapter(
    private var companyList: List<Company>, private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCompanyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            binding.companyData = company
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCompanyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = companyList[position]
        holder.bind(company = data)
        holder.binding.root.setOnClickListener {
            onItemClick(data.id)
        }
    }

    fun updateData(newCompanies: List<Company>) {
        companyList = newCompanies
        notifyDataSetChanged()
    }

}

