package com.cocaine.myply.feature.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.UserKeywordUpdateData
import com.cocaine.myply.feature.ui.mypage.TagUseCase
import com.cocaine.myply.feature.ui.mypage.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupKeywordViewModel @Inject constructor(
    private val tagUseCase: TagUseCase,
    private val userInfoUseCase: UserInfoUseCase
) : BaseViewModel() {

    // 키워드 편집에서 선택 가능한 키워드
    private val _keywords = MutableLiveData<List<Pair<String, Boolean>>>()
    val keywords: LiveData<List<Pair<String, Boolean>>> = _keywords

    fun loadRecommendTags() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            tagUseCase.getRecommendTags().data.tags.let { tag ->
                _keywords.postValue(tag.map { it to false })
            }
        }
    }

    fun updateKeywordSelectedState(keyword: String) {
        _keywords.value = _keywords.value?.map { (currentKeyword, isSelected) ->
            if (currentKeyword == keyword) {
                currentKeyword to !isSelected
            } else {
                currentKeyword to isSelected
            }
        }?.toList()
    }

    fun updateMyKeyword() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _keywords.value?.filter { it.second }?.map { it.first }?.let {
                userInfoUseCase.updateUserKeyword(UserKeywordUpdateData(it))
            }
        }
    }
}

