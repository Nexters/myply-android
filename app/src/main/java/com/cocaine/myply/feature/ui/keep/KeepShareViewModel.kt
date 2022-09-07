package com.cocaine.myply.feature.ui.keep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.ShareColorItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeepShareViewModel @Inject constructor(): ViewModel() {
    private val _selectedColorPair = MutableLiveData<ShareColorItem>(ShareColorItem.Frame1)
    val selectedColorPair: LiveData<ShareColorItem> = _selectedColorPair

    private val _memoData = MutableLiveData<MemoInfo>()
    val memoData: LiveData<MemoInfo> = _memoData

    fun updateSelectedViewFrameColor(colorPair: ShareColorItem) {
        _selectedColorPair.value = colorPair
    }

    fun setMemoData(memoData: MemoInfo) {
        _memoData.value = memoData
    }
}