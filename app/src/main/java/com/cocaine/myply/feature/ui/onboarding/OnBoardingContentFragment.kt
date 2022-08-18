package com.cocaine.myply.feature.ui.onboarding

import androidx.core.os.bundleOf
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentOnboardingContentBinding

class OnBoardingContentFragment :
    BaseFragment<FragmentOnboardingContentBinding>(R.layout.fragment_onboarding_content) {
    companion object {
        const val ONBOARDING_TXT_KEY = "onboarding_text"
        const val ONBOARDING_IMG_KEY = "onboarding_img"

        fun instance(stringId: Int, imgId: Int): OnBoardingContentFragment {
            return OnBoardingContentFragment().apply {
                arguments = bundleOf(ONBOARDING_TXT_KEY to stringId, ONBOARDING_IMG_KEY to imgId)
            }
        }
    }

    override fun setup() {
        arguments?.let { args ->
            binding?.onboardingImg?.setImageResource(args.getInt(ONBOARDING_IMG_KEY))
            binding?.onboardingTxt?.text = getText(args.getInt(ONBOARDING_TXT_KEY))
        }
    }
}