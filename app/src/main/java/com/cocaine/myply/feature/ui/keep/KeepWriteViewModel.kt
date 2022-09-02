package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.MemoUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KeepWriteViewModel @Inject constructor(private val usecase: KeepWriteUsecase) : BaseViewModel() {
    private val _countWord = MutableLiveData<Int>(0)
    val countWord: LiveData<Int> = _countWord

    val memoBody = MutableLiveData<String>()

    private val _memoData = MutableLiveData<MemoInfo>()
    val memoData: LiveData<MemoInfo> = _memoData

    fun updateWordCount(length: Int) {
        _countWord.value = length
    }

    fun setMemoData(memoData: MemoInfo) {
        _memoData.value = memoData
        memoBody.value = memoData.body
    }

    fun updateMemo(body: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val memoUpdate = MemoUpdate(body)

            _memoData.value?.memoID?.let {
                val updateResult = usecase.updateMemo(it, memoUpdate)

                withContext(Dispatchers.Main) {
                    _memoData.value = updateResult
                }
            }
        }
    }
}