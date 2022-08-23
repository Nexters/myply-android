package com.cocaine.myply.feature.ui.keep

import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepDetailBinding
import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY
import com.google.android.material.chip.Chip
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

        viewModel.memoDetail.value?.let { memo ->
            viewModel.getMemo(memo.memoID)
        }
    }

    private fun setMemoDetail() {
        val data = arguments?.getParcelable<MemoResponse>(MEMO_KEY)
        data?.let {
            viewModel.updateMemoData(it)

            for(i in it.keywords) {
                Chip(requireContext()).apply {
                    text = i
                    binding?.keepDetailPlaylist?.playlistTags?.addView(this)
                }
            }
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
}