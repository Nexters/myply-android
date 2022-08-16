package com.cocaine.myply.feature.ui.onboarding

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cocaine.myply.R

class OnBoardingFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = OnBoardingFragment.PAGE_SIZE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnBoardingContentFragment().apply {
                    val bundle = bundleOf(
                        OnBoardingContentFragment.ONBOARDING_IMG_KEY to R.drawable.ic_onboarding_1,
                        OnBoardingContentFragment.ONBOARDING_TXT_KEY to R.string.onboarding_1
                    )

                    arguments = bundle
                }
            }
            1 -> {
                OnBoardingContentFragment().apply {
                    val bundle = bundleOf(
                        OnBoardingContentFragment.ONBOARDING_IMG_KEY to R.drawable.ic_onboarding_2,
                        OnBoardingContentFragment.ONBOARDING_TXT_KEY to R.string.onboarding_2
                    )

                    arguments = bundle
                }
            }
            else -> {
                OnBoardingContentFragment().apply {
                    val bundle = bundleOf(
                        OnBoardingContentFragment.ONBOARDING_IMG_KEY to R.drawable.ic_onboarding_3,
                        OnBoardingContentFragment.ONBOARDING_TXT_KEY to R.string.onboarding_3
                    )

                    arguments = bundle
                }
            }
        }
    }

}