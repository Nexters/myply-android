package com.cocaine.myply.feature.ui.keep

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepWriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeepWriteFragment: BaseFragment<FragmentKeepWriteBinding>(R.layout.fragment_keep_write) {
    private val viewModel: KeepWriteViewModel by viewModels()

    override fun setup() {
        binding?.view = this
        binding?.viewmodel = viewModel

        binding?.keepWriteMemo?.addTextChangedListener {
            it?.length?.let { length -> viewModel.updateWordCount(length) }
        }
    }
}