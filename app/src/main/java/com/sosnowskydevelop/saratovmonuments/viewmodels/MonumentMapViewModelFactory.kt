package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentMapViewModelFactory(
    private val monumentsRepository: MonumentsRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MonumentMapViewModel(
            monumentsRepository = monumentsRepository,
        ) as T
    }
}