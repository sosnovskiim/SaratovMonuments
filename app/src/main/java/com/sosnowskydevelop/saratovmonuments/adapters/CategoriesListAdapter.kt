package com.sosnowskydevelop.saratovmonuments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.saratovmonuments.data.Category
import com.sosnowskydevelop.saratovmonuments.databinding.ListItemCategoryBinding
import com.sosnowskydevelop.saratovmonuments.viewmodels.CategoriesListItemViewModel

class CategoriesListAdapter(
    private val categories: Array<Category>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = CategoriesListItemViewModel(category = categories[position])
        holder.binding.categoryName.setOnClickListener {
            Toast.makeText(
                fragment.requireContext(),
                "Следующий фрагмент пока не готов(",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount() = categories.size
}