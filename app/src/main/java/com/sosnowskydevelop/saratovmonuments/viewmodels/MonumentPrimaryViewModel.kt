package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.Category
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentPrimaryViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val monumentsRepository: MonumentsRepository,
) : ViewModel() {
    private var category: Category? = null
    val categoryName: String? get() = category?.name
    private var monument: Monument? = null
    val monumentPhotoName: String? get() = monument?.photoName
    val monumentName: String? get() = monument?.name
    val monumentId: Long? get() = monument?.id

    fun initData(
        categoryId: Long?,
        monumentId: Long?,
    ) {
        if (categoryId != null) {
            category = categoriesRepository.getCategory(categoryId = categoryId)
        }
        if (monumentId != null) {
            monument = monumentsRepository.getMonument(monumentId = monumentId)
        }
    }
}