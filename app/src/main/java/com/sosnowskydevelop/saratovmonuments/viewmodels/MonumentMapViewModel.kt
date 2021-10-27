package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.data.MonumentsRepository

class MonumentMapViewModel(
    private val monumentsRepository: MonumentsRepository,
) : ViewModel() {
    private var monument: Monument? = null
    val monumentName: String? get() = monument?.name
    val monumentPointLatitude: Double? get() = monument?.pointLatitude
    val monumentPointLongitude: Double? get() = monument?.pointLongitude

    fun initData(
        monumentId: Long,
    ) {
        monument = monumentsRepository.getMonument(monumentId = monumentId)
    }
}