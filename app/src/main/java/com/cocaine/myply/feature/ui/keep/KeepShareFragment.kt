package com.cocaine.myply.feature.ui.keep

import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentShareBinding
import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.data.model.ShareColorItem
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY
import java.io.File

class KeepShareFragment : BaseFragment<FragmentShareBinding>(R.layout.fragment_share) {
    private val viewModel: KeepShareViewModel by viewModels()
    private lateinit var adapter: KeepShareAdapter

    override fun setup() {
        binding?.view = this
        binding?.viewmodel = viewModel

        setRecyclerView()
        getMemoData()
    }

    private fun setRecyclerView() {
        adapter = KeepShareAdapter(::getSelectedColorItem, ::updateSelectedColorItem)
        binding?.shareColorList?.adapter = adapter
    }

    private fun getMemoData() {
        arguments?.getParcelable<MemoResponse>(MEMO_KEY)?.let {
            viewModel.setMemoData(it)
        }
    }

    fun showShareMenu() {
        binding?.keepShareView?.let { saveView ->
            val modal = KeepShareBottomSheetDialog(saveView)
            modal.show(parentFragmentManager, modal.tag)
        }
    }

    fun updateSelectedColorItem(shareColorItem: ShareColorItem) {
        viewModel.updateSelectedViewFrameColor(shareColorItem)
    }

    fun getSelectedColorItem(): ShareColorItem = viewModel.selectedColorPair.value!!
}