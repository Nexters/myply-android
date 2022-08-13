package com.cocaine.myply.feature.ui.keep

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import androidx.databinding.DataBindingUtil
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseBottomSheetDialogFragment
import com.cocaine.myply.databinding.MenuShareBinding
import com.cocaine.myply.databinding.MyplyKeepToastBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.OutputStream

class KeepShareBottomSheetDialog(private val saveView: View) :
    BaseBottomSheetDialogFragment<MenuShareBinding>(R.layout.menu_share) {

    companion object {
        private const val writePermission = "android.permission.WRITE_EXTERNAL_STORAGE"
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGrant ->
            if (isGrant) {
                saveViewToImage()
            }
        }

    override fun setup() {
        binding?.view = this

        dialog?.setOnShowListener {
            binding?.shareMenu?.let { menu ->
                BottomSheetBehavior.from(menu).apply {
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    fun saveViewToImage() {
        checkPermission()

        val bitmap = saveView.drawToBitmap()
        val appContext = requireActivity().applicationContext
        val resolver = appContext.contentResolver

        val result = saveImage(resolver, bitmap)

        if (result != null) {
            /**
             * Android 30 이후로는 커스텀 토스트가 deprecated 되었습니다.
             * 이에 따라, 기본과 커스텀을 분기처리하여 생성합니다.
             * */
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                Toast(requireContext()).apply {
                    val binding = DataBindingUtil.inflate<MyplyKeepToastBinding>(
                        layoutInflater,
                        R.layout.myply_keep_toast,
                        null,
                        false
                    )
                    view = binding.root

                    show()
                }
            } else {
                Toast.makeText(requireContext(), R.string.toast_save_image, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        dismiss()
    }

    fun shareToInstagram() {
        checkPermission()

        val bitmap = saveView.drawToBitmap()
        val resolver = requireActivity().applicationContext.contentResolver
        val uri = saveImage(resolver, bitmap)

        kotlin.runCatching {
            saveViewToImage()
            Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                requireActivity().startActivity(
                    Intent.createChooser(
                        this,
                        resources.getString(R.string.keep_share)
                    )
                )
            }
        }

        dismiss()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                writePermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(writePermission)
        }
    }

    private fun saveImage(resolver: ContentResolver, bitmap: Bitmap): Uri? {
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