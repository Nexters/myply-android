package com.cocaine.myply.feature.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.data.model.UserNameUpdateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val userInfoUseCase: UserInfoUseCase) :
    BaseViewModel() {

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

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userInfoUseCase.getUserInfo().data.let {
                nickname.postValue(it.name)
                _keywords.postValue(it.keywords)
            }
        }
    }

    fun updateKeywords() {
        val keywords = _keywords.value ?: return

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userInfoUseCase.updateUserKeyword(UserKeywordUpdateData(keywords))
        }
    }

    fun updateNickname() {
        val name = nickname.value ?: return

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userInfoUseCase.updateUserName(UserNameUpdateData(name))
        }
    }
}
