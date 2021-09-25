package com.sosnowskydevelop.saratovmonuments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.data.Category
import com.sosnowskydevelop.saratovmonuments.databinding.ItemCategoryBinding
import com.sosnowskydevelop.saratovmonuments.utilities.BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
import com.sosnowskydevelop.saratovmonuments.utilities.REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
import com.sosnowskydevelop.saratovmonuments.viewmodels.CategoriesItemViewModel

class CategoriesListAdapter(
    private val categories: Array<Category>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = CategoriesItemViewModel(category = categories[position])
        holder.binding.categoryName.setOnClickListener {
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS,
                result = bundleOf(
                    BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
                            to categories[position].id
                )
            )
            fragment.findNavController()
                .navigate(R.id.action_categoriesFragment_to_monumentsFragment)
        }
    }

    override fun getItemCount(): Int = categories.size
}