package com.cocaine.myply.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {

    private val _isUserRegistered = MutableLiveData<Boolean>()
    val isUserRegistered: LiveData<Boolean> = _isUserRegistered

    fun getUserRegisterState() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val data = userUseCase.getUserRegisterState()
            if (data.isSuccessful) {
                _isUserRegistered.postValue(data.body()?.data?.name?.isNotEmpty())
            } else {
                _isUserRegistered.postValue(false)
            }
        }
    }
}
