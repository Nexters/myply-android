package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import javax.inject.Inject

class KeepWriteViewModel @Inject constructor() : BaseViewModel() {
    private val _countWord = MutableLiveData<Int>(0)
    val countWord: LiveData<Int> = _countWord

    fun updateWordCount(length: Int) {
        _countWord.value = length
    }
}