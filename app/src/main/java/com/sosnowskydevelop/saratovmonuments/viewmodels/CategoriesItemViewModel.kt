package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Category

class CategoriesItemViewModel(
    private val category: Category,
) : ViewModel() {
    val categoryName: String get() = category.name
}