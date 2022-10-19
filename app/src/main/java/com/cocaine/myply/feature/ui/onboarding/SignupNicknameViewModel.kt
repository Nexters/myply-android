package com.cocaine.myply.feature.ui.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupNicknameViewModel @Inject constructor(
    private val userAuthUseCase: UserAuthUseCase
) : BaseViewModel() {

    val nickname = MutableLiveData<String>()

    fun signupUser() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            nickname.value?.let {
                userAuthUseCase.signupUser(
                    nickname = it,
                    keywords = emptyList()
                )
            }
        }
    }
}
