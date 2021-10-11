package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentPrimaryViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
    private val monumentsRepository: MonumentsRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MonumentPrimaryViewModel(
            categoriesRepository = categoriesRepository,
            monumentsRepository = monumentsRepository,
        ) as T
    }
}