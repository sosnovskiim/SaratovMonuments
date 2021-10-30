package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentSearchViewModel(
    private val monumentsRepository: MonumentsRepository,
) : ViewModel() {
    private var categoryId: Long? = null
    var monuments: Array<Monument> = arrayOf()

    fun initData(categoryId: Long) {
        this.categoryId = categoryId
    }

    fun updateMonuments(searchRequest: String) {
        monuments = monumentsRepository.getMonumentsBySearchRequest(
            searchRequest = searchRequest,
            categoryId = categoryId,
        )
    }
}