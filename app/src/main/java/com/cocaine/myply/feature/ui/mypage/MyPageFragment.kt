package com.cocaine.myply.feature.ui.mypage

import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentMypageBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override fun setup() {
        binding?.mypageMyKeywordList?.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }

        binding?.mypageMyKeywordEdit?.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_mypageEditFragment)
        }
        binding?.mypageEditNicknameMore?.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_mypageNicknameFragment)
        }
    }
}
