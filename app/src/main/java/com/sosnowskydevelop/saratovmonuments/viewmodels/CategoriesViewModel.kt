package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.Category

class CategoriesViewModel(
    categoriesRepository: CategoriesRepository,
) : ViewModel() {
    val categories: Array<Category> = categoriesRepository.getCategories()
}