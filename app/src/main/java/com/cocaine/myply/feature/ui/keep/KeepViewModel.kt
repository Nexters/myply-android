package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KeepViewModel @Inject constructor(private val usecase: KeepUsecase) : BaseViewModel() {
    private val _userMemoList = MutableLiveData<List<MemoResponse>>()
    val userMemoList: LiveData<List<MemoResponse>> = _userMemoList

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = usecase.getUserMemoList()

            withContext(Dispatchers.Main) {
                _userMemoList.value = result.data?.memos
            }
        }
    }
}