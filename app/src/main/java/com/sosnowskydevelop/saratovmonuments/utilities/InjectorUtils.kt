package com.sosnowskydevelop.saratovmonuments.utilities

import android.content.Context
import com.sosnowskydevelop.saratovmonuments.data.CategoriesRepository
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository
import com.sosnowskydevelop.saratovmonuments.viewmodels.*

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    private fun getMonumentsRepository(context: Context) =
        MonumentsRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) =
        CategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
        )

    fun provideMonumentSearchViewModelFactory(context: Context) =
        MonumentSearchViewModelFactory(
            monumentsRepository = getMonumentsRepository(context = context),
        )

    fun provideMonumentsViewModelFactory(context: Context) =
        MonumentsViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
            monumentsRepository = getMonumentsRepository(context = context),
        )

    fun provideMonumentPrimaryViewModelFactory(context: Context) =
        MonumentPrimaryViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
            monumentsRepository = getMonumentsRepository(context = context),
        )

    fun provideMonumentDescriptionViewModelFactory(context: Context) =
        MonumentDescriptionViewModelFactory(
            monumentsRepository = getMonumentsRepository(context = context),
        )

    fun provideMonumentMapViewModelFactory(context: Context) =
        MonumentMapViewModelFactory(
            monumentsRepository = getMonumentsRepository(context = context),
        )
}