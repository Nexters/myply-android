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
                OnBoardingContentFragment.instance(R.string.onboarding_1, R.drawable.ic_onboarding_1)
            }
            1 -> {
                OnBoardingContentFragment.instance(R.string.onboarding_2, R.drawable.ic_onboarding_2)
            }
            else -> {
                OnBoardingContentFragment.instance(R.string.onboarding_3, R.drawable.ic_onboarding_3)
            }
        }
    }

}