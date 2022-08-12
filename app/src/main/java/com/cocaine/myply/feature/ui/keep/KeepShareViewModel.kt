package com.cocaine.myply.feature.ui.keep

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocaine.myply.feature.data.model.ShareColorItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.OutputStream
import javax.inject.Inject

@HiltViewModel
class KeepShareViewModel @Inject constructor(): ViewModel() {
    private val _selectedColorPair = MutableLiveData<ShareColorItem>(ShareColorItem.Frame1)
    val selectedColorPair: LiveData<ShareColorItem> = _selectedColorPair

    fun updateSelectedViewFrameColor(colorPair: ShareColorItem) {
        _selectedColorPair.value = colorPair
    }
}