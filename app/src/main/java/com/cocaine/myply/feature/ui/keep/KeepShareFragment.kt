package com.cocaine.myply.feature.ui.keep

import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentShareBinding
import java.io.File

class KeepShareFragment : BaseFragment<FragmentShareBinding>(R.layout.fragment_share) {
    private val viewModel: KeepShareViewModel by viewModels()
    private lateinit var adapter: KeepShareAdapter

    override fun setup() {
        binding?.view = this
        binding?.viewmodel = viewModel
        adapter = KeepShareAdapter(viewModel)
        binding?.shareColorList?.adapter = adapter
    }

    fun showShareMenu() {
        binding?.keepShareView?.let { saveView ->
            val modal = KeepShareBottomSheetDialog(saveView, viewModel)
            modal.show(parentFragmentManager, modal.tag)
        }
    }
}