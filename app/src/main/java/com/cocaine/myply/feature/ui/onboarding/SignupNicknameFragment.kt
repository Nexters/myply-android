package com.cocaine.myply.feature.ui.onboarding

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentSignupNicknameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupNicknameFragment :
    BaseFragment<FragmentSignupNicknameBinding>(R.layout.fragment_signup_nickname) {

    private val viewModel: SignupNicknameViewModel by viewModels()

    override fun setup() {
        binding?.viewModel = viewModel

        binding?.signupNicknameBtn?.setOnClickListener {
            viewModel.signupUser()

            findNavController().let { controller ->
                if (controller.currentDestination?.id == R.id.signupNicknameFragment) {
                    controller.navigate(R.id.action_signupNicknameFragment_to_signupKeywordFragment)
                }
            }
        }
    }
}
