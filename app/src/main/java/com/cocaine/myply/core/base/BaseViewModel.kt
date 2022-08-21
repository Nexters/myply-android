package com.cocaine.myply.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel: ViewModel() {
    private val _errMsg = MutableLiveData<Event<String>>()
    var errMsg: LiveData<Event<String>> = _errMsg

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwalbe ->
        throwalbe.printStackTrace()

        _errMsg.postValue(Event("error: ${throwalbe.message}"))
    }
}