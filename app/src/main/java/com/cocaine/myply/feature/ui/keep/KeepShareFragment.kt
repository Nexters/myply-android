package com.cocaine.myply.feature.ui.keep

import androidx.fragment.app.viewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentShareBinding
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.ShareColorItem
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY

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
        arguments?.getParcelable<MemoInfo>(MEMO_KEY)?.let {
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