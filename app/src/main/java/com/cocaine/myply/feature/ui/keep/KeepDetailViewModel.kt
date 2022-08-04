package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import javax.inject.Inject

class KeepDetailViewModel @Inject constructor(): BaseViewModel() {
    private val _isEditable = MutableLiveData<Boolean>(false)
    val isEditable: LiveData<Boolean> = _isEditable

    fun updateIsEditable() {
        _isEditable.value = _isEditable.value?.not()
    }
}