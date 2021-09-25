package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentsViewModelFactory(
    private val monumentsRepository: MonumentsRepository,
    private val categoriesRepository: CategoriesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MonumentsViewModel(
            monumentsRepository = monumentsRepository,
            categoriesRepository = categoriesRepository,
        ) as T
    }
}