package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentDescriptionViewModel(
    private val monumentsRepository: MonumentsRepository,
) : ViewModel() {
    private var monument: Monument? = null
    val monumentName: String? get() = monument?.name
    val monumentInstallationDate: String? get() = monument?.installationDate
    val monumentAuthors: String? get() = monument?.authors
    val monumentDescription: String? get() = monument?.description
    val monumentLinks: String? get() = monument?.links

    fun initData(
        monumentId: Long,
    ) {
        monument = monumentsRepository.getMonument(monumentId = monumentId)
    }
}