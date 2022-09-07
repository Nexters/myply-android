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
class MyPageViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val tagUseCase: TagUseCase
) : BaseViewModel() {

    val nickname = MutableLiveData<String>()

    // 서버에 반영된 선택된 키워드
    private val _keywords = MutableLiveData<List<String>>()
    val keywords: LiveData<List<String>> = _keywords

    // 키워드 편집에서 선택 가능한 키워드
    private val _baseKeywords = MutableLiveData<List<Pair<String, Boolean>>>()
    val baseKeywords: LiveData<List<Pair<String, Boolean>>> = _baseKeywords

    // 키워드 편집에서 선택된 키워드
    private val _clickedKeywords = MutableLiveData<List<String>>()
    val clickedKeywords: LiveData<List<String>> = _clickedKeywords

    fun loadMyPageInfo() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userInfoUseCase.getUserInfo().data.let { data ->
                nickname.postValue(data.name)
                _keywords.postValue(data.keywords)
            }
        }
    }

    fun updateKeywords() {
        val keywords = _clickedKeywords.value ?: return

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

    fun loadRecommendTags() {
        val keywords = _keywords.value ?: return

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            tagUseCase.getRecommendTags().data.tags.let { tag ->
                _baseKeywords.postValue(tag.map { it to (it in keywords) })
                _clickedKeywords.postValue(tag.filter { it in keywords })
            }
        }
    }

    fun updateKeywordClickStatus(keyword: String) {
        _baseKeywords.value = _baseKeywords.value?.map {
            val (baseKeyword, clicked) = it
            if (keyword == baseKeyword) {
                if (clicked) {
                    _clickedKeywords.value = _clickedKeywords.value?.minus(baseKeyword)
                } else {
                    _clickedKeywords.value = _clickedKeywords.value?.toMutableList()?.apply {
                        this.add(0, baseKeyword)
                    }
                }
                baseKeyword to !clicked
            } else {
                it
            }
        }
    }
}
