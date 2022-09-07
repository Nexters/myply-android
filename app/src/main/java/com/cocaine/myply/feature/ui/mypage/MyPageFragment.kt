package com.cocaine.myply.feature.ui.mypage

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentMypageBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val myPageViewModel: MyPageViewModel by activityViewModels()

    private lateinit var myPageKeywordAdapter: MyPageKeywordAdapter

    override fun setup() {
        binding?.viewModel = myPageViewModel

        setKeywordAdapter()
        setNavigateClickListener()
        setKeywordObserver()
    }

    override fun onResume() {
        super.onResume()
        myPageViewModel.loadMyPageInfo()
    }

    private fun setKeywordAdapter() {
        myPageKeywordAdapter = MyPageKeywordAdapter()

        binding?.mypageMyKeywordList?.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }

        binding?.mypageMyKeywordList?.adapter = myPageKeywordAdapter
    }

    private fun setNavigateClickListener() {
        binding?.mypageMyKeywordEdit?.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_mypageEditFragment)
        }
        binding?.mypageEditNicknameMore?.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_mypageNicknameFragment)
        }
    }

    private fun setKeywordObserver() {
        myPageViewModel.keywords.observe(viewLifecycleOwner) {
            myPageKeywordAdapter.submitList(it)
        }
    }
}
