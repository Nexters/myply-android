package com.cocaine.myply.feature.ui.keep

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepWriteBinding
import com.cocaine.myply.feature.data.model.MemoResponse
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeepWriteFragment: BaseFragment<FragmentKeepWriteBinding>(R.layout.fragment_keep_write) {
    private val viewModel: KeepWriteViewModel by viewModels()

    override fun setup() {
        binding?.view = this
        binding?.viewmodel = viewModel

        val data = arguments?.getParcelable<MemoResponse>(MEMO_KEY)
        data?.let { viewModel.setMemoData(it) }

        binding?.keepWriteMemo?.addTextChangedListener {
            it?.length?.let { length -> viewModel.updateWordCount(length) }
        }
    }

    fun updateMemo(body: String) {
        viewModel.updateMemo(body)

        findNavController().popBackStack()
    }
}