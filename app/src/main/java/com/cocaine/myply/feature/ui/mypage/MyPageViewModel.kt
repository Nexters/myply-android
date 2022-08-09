package com.cocaine.myply.feature.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): BaseViewModel() {

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    private val _keywords = MutableLiveData<List<String>>()
    val keywords: LiveData<List<String>> = _keywords
}
