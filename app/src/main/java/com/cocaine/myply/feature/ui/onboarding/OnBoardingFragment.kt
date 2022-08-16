package com.cocaine.myply.feature.ui.onboarding

import androidx.viewpager2.widget.ViewPager2
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {
    companion object {
        const val PAGE_SIZE = 3
    }

    private val callback = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            when(position) {
                PAGE_SIZE - 1 -> {
                    binding?.onboardingBtn?.apply {
                        text = getString(R.string.onboarding_start_btn)

                        //TODO : Movoe to sign up
                    }
                }
                else -> {
                    binding?.onboardingBtn?.apply {
                        text = getString(R.string.onboarding_next)
                        setOnClickListener {
                            binding?.onboardingPager?.currentItem = position + 1
                        }
                    }
                }
            }
        }
    }

    override fun setup() {
        binding?.let { bind ->
            bind.onboardingPager.adapter = OnBoardingFragmentAdapter(requireActivity())
            bind.onboardingPager.registerOnPageChangeCallback(callback)
            TabLayoutMediator(
                bind.onboardingTabIndicator,
                bind.onboardingPager
            ) { _, _ -> }.attach()
        }
    }

    override fun goBack() {
        binding?.onboardingPager?.unregisterOnPageChangeCallback(callback)
        super.goBack()
    }
}