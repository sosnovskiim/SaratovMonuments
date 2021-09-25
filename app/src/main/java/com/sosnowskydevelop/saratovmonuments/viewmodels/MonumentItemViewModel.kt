package com.sosnowskydevelop.saratovmonuments.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovmonuments.data.Monument

class MonumentItemViewModel(
    private val monument: Monument,
) : ViewModel() {
    val monumentName: String get() = monument.name
}