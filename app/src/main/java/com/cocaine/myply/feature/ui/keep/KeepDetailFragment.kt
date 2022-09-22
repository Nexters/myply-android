package com.cocaine.myply.feature.ui.keep

import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepDetailBinding
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.ui.dialog.MyPlyTwoButtonDialog
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeepDetailFragment: BaseFragment<FragmentKeepDetailBinding>(R.layout.fragment_keep_detail) {
    private val viewModel: KeepDetailViewModel by viewModels()

    override fun setup() {
        binding?.view = this
        binding?.viewModel = viewModel

        setMemoDetail()
        binding?.keepDetailMemo?.addTextChangedListener {
            it?.length?.let { len -> viewModel.updateIsWritable(len) }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getMemo()
    }

    private fun setMemoDetail() {
        val adapter = KeepTagAdapter()
        binding?.keepDetailPlaylist?.playlistTags?.layoutManager = FlexboxLayoutManager(requireContext()).apply{
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
        val data = arguments?.getParcelable<MemoInfo>(MEMO_KEY)

        binding?.keepDetailPlaylist?.playlistTags?.adapter = adapter
        data?.let {
            viewModel.updateMemoData(it)
            it.keywords?.let { tags -> adapter.submitList(tags)}
        }
    }

    fun moveToKeepWrite() {
        val controller = findNavController()
        if(controller.currentDestination?.id == R.id.keepDetailFragment) {
            val memoData = bundleOf(MEMO_KEY to viewModel.memoDetail.value)
            controller.navigate(R.id.action_keepDetailFragment_to_keepWriteFragment, memoData)
        }
    }

    fun moveToKeepShare() {
        val controller = findNavController()

        if(controller.currentDestination?.id == R.id.keepDetailFragment) {
            val memoData = bundleOf(MEMO_KEY to viewModel.memoDetail.value)
            controller.navigate(R.id.action_keepDetailFragment_to_keepShareFragment, memoData)
        }
    }

    private fun showDeleteMemoDialog() {
        MyPlyTwoButtonDialog.setDialogContent("", "", "삭제", "취소", {}, {})
        findNavController().navigate(R.id.action_keepDetailFragment_to_myPlyTwoButtonDialog)
    }

    fun deleteMemo() {
        android.util.Log.e("clicked", "heart")
        viewModel.memoDetail.value?.body?.let {
            if(it.length > 0) {
                showDeleteMemoDialog()
            }
        }
    }
}