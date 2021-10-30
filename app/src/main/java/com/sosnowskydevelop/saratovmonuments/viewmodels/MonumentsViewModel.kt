package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.Category
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentsViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val monumentsRepository: MonumentsRepository,
) : ViewModel() {
    private var category: Category? = null
    val categoryId: Long? get() = category?.id
    val categoryName: String? get() = category?.name
    var monuments: Array<Monument> = arrayOf()

    fun initData(
        categoryId: Long,
    ) {
        category = categoriesRepository.getCategory(categoryId = categoryId)
        monuments = monumentsRepository.getMonuments(categoryId = categoryId)
    }
}