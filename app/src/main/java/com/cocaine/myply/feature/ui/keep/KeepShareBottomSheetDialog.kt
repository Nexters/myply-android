package com.cocaine.myply.feature.ui.keep

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.google.android.material.snackbar.Snackbar
import java.io.OutputStream

class KeepShareBottomSheetDialog(
    private val saveView: View,
    private val keepShareViewModel: KeepShareViewModel
) :
    BaseBottomSheetDialogFragment<MenuShareBinding>(R.layout.menu_share) {
    private val writePermission = "android.permission.WRITE_EXTERNAL_STORAGE"
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

        val result = keepShareViewModel.saveImage(resolver, bitmap)

        if(result != null) {
            /**
             * Android 30 이후로는 커스텀 토스트가 deprecated 되었습니다.
             * 이에 따라, 기본과 커스텀을 분기처리하여 생성합니다.
             * */
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
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
                Toast.makeText(requireContext(), R.string.toast_save_image, Toast.LENGTH_SHORT).show()
            }
        }

        dismiss()
    }

    fun shareToInstagram() {
        checkPermission()

        val bitmap = saveView.drawToBitmap()
        val resolver = requireActivity().applicationContext.contentResolver
        val uri = keepShareViewModel.saveImage(resolver, bitmap)

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
}