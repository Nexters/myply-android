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
    private val _selectedColorPair = MutableLiveData<ShareColorItem>()
    val selectedColorPair: LiveData<ShareColorItem> = _selectedColorPair

    fun updateSelectedViewFrameColor(colorPair: ShareColorItem) {
        _selectedColorPair.value = colorPair
    }

    fun saveImage(resolver: ContentResolver, bitmap: Bitmap): Uri? {
        val newImage = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${System.nanoTime()}.png")
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaStore.Images.ImageColumns.RELATIVE_PATH,
                    "${Environment.DIRECTORY_PICTURES}"
                )
            }
        }

        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var stream: OutputStream? = null
        var uri: Uri? = null

        try {
            uri = resolver.insert(contentUri, newImage)
            if (uri == null) return null

            stream = resolver.openOutputStream(uri)
            if (stream == null) return null

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

            return uri

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}