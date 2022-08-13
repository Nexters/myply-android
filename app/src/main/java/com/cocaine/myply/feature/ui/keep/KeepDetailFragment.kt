package com.cocaine.myply.feature.ui.keep

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeepDetailFragment: BaseFragment<FragmentKeepDetailBinding>(R.layout.fragment_keep_detail) {
    private val viewModel: KeepDetailViewModel by viewModels()

    override fun setup() {
        binding?.view = this
        binding?.viewModel = viewModel

        binding?.keepDetailMemo?.addTextChangedListener {
            it?.length?.let { len -> viewModel.updateIsWritable(len) }
        }
    }

    fun moveToKeepWrite() {
        val controller = findNavController()
        if(controller.currentDestination?.id == R.id.keepDetailFragment) {
            controller.navigate(R.id.action_keepDetailFragment_to_keepWriteFragment)
        }
    }

    fun moveToKeepShare() {
        val controller = findNavController()

        if(controller.currentDestination?.id == R.id.keepDetailFragment) {
            controller.navigate(R.id.action_keepDetailFragment_to_keepShareFragment)
        }
    }
}