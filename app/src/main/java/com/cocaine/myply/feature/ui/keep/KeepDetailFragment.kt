package com.cocaine.myply.feature.ui.keep

import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeepDetailFragment: BaseFragment<FragmentKeepDetailBinding>(R.layout.fragment_keep_detail) {
    @Inject lateinit var viewModel: KeepDetailViewModel

    override fun setup() {
        binding?.view = this
        binding?.viewModel = viewModel

        binding?.keepDetailEditBtn?.setOnClickListener { viewModel.updateIsEditable() }
    }
}