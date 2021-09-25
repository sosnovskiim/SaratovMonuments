package com.sosnowskydevelop.saratovmonuments.utilities

import android.content.Context
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository
import com.sosnowskydevelop.saratovmonuments.viewmodels.CategoriesViewModelFactory
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentsViewModelFactory

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    private fun getMonumentsRepository(context: Context) =
        MonumentsRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) = CategoriesViewModelFactory(
        categoriesRepository = getCategoriesRepository(context = context),
    )

    fun provideMonumentsViewModelFactory(context: Context) = MonumentsViewModelFactory(
        monumentsRepository = getMonumentsRepository(context = context),
        categoriesRepository = getCategoriesRepository(context = context),
    )
}