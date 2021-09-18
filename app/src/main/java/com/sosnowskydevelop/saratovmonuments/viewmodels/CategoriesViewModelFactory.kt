package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository

class CategoriesViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(
            categoriesRepository = categoriesRepository,
        ) as T
    }
}