package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cocaine.myply.core.base.BaseViewModel
import com.cocaine.myply.feature.data.model.MemoInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class KeepDetailViewModel @Inject constructor(private val usecase: KeepDetailUsecase): BaseViewModel() {
    private val _isWrited = MutableLiveData<Boolean>(false)
    val isWrited: LiveData<Boolean> = _isWrited

    private val _memoDetail = MutableLiveData<MemoInfo>()
    val memoDetail: LiveData<MemoInfo> = _memoDetail

    fun updateIsWritable(writeCount: Int) {
        _isWrited.value = writeCount > 0
    }

    fun updateMemoData(memoResponse: MemoInfo) {
        _memoDetail.value = memoResponse
    }

    fun getMemo() {
        _memoDetail.value?.let { memo ->
            viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                val result = usecase.getMemo(memo.memoID)

                withContext(Dispatchers.Main) {
                    _memoDetail.value = result
                }
            }
        }
    }
}