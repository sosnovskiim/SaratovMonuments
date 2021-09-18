package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Category

class CategoriesListItemViewModel(
    private val category: Category,
) : ViewModel() {
    val categoryName: String
        get() {
            return category.name
        }
}