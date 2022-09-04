package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeepViewModel @Inject constructor(private val usecase: KeepUsecase) : BaseViewModel() {
    private val _userMemoList = MutableLiveData<List<MemoInfo>>()
    val userMemoList: LiveData<List<MemoInfo>> = _userMemoList

    init {
        getKeepUserMemoList()
    }

    fun getKeepUserMemoList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = usecase.getUserMemoList()
            _userMemoList.postValue(result.data?.memos)
        }
    }
}