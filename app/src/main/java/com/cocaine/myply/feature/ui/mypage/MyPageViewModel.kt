package com.cocaine.myply.feature.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cocaine.myply.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): BaseViewModel() {

    val nickname = MutableLiveData<String>()

    // 서버에 반영된 선택된 키워드
    private val _keywords = MutableLiveData<List<String>>()
    val keywords: LiveData<List<String>> = _keywords

    // 선택가능한 전체 키워드 목록
    private val _baseKeywords = MutableLiveData<List<String>>()
    val baseKeywords: LiveData<List<String>> = _baseKeywords

    // 사용자가 편집하면서 선택한 키워드
    private val _checkedKeywords = MutableLiveData<List<String>>()
    val checkedKeywords: LiveData<List<String>> = _checkedKeywords

    fun updateKeywords() {
        // TODO 새로운 키워드 목록을 서버에 반영
    }

    fun updateNickname() {
        // TODO 새로운 닉네임을 서버에 반영
    }
}