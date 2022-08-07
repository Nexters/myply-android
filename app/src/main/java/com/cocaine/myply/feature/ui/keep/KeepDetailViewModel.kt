package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import javax.inject.Inject

class KeepDetailViewModel @Inject constructor(): BaseViewModel() {
    private val _isWrited = MutableLiveData<Boolean>(false)
    val isWrited: LiveData<Boolean> = _isWrited

    fun updateIsWritable(writeCount: Int) {
        _isWrited.value = writeCount > 0
    }
}