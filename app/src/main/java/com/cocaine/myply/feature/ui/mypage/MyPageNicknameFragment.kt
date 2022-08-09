package com.cocaine.myply.feature.ui.mypage

import androidx.fragment.app.activityViewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentMypageNicknameBinding

class MyPageNicknameFragment :
    BaseFragment<FragmentMypageNicknameBinding>(R.layout.fragment_mypage_nickname) {

    private val myPageViewModel: MyPageViewModel by activityViewModels()

    override fun setup() {
        binding?.viewModel = myPageViewModel

        setNicknameUpdateConfirmClickListener()
    }

    private fun setNicknameUpdateConfirmClickListener() {
        binding?.mypageNicknameBtn?.setOnClickListener {
            myPageViewModel.updateNickname()
        }
    }
}
