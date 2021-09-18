package com.sosnowskydevelop.saratovmonuments.utilities

import android.content.Context
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.viewmodels.CategoriesViewModelFactory

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) = CategoriesViewModelFactory(
        categoriesRepository = getCategoriesRepository(context = context)
    )
}