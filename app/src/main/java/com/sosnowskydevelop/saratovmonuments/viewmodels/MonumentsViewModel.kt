package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.Category
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentsViewModel(
    private val monumentsRepository: MonumentsRepository,
    private val categoriesRepository: CategoriesRepository,
) : ViewModel() {
    var monuments: Array<Monument> = arrayOf()
    private var category: Category? = null
    val categoryId: Long? get() = category?.id
    val categoryName: String? get() = category?.name

    fun initMonuments(categoryId: Long) {
        monuments = monumentsRepository.getMonuments(categoryId = categoryId)
        category = categoriesRepository.getCategory(categoryId = categoryId)
    }
}