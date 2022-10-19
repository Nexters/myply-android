package com.cocaine.myply.feature.ui.onboarding

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentSignupKeywordBinding
import com.cocaine.myply.feature.ui.mypage.MyPageEditKeywordAdapter
import com.cocaine.myply.feature.ui.mypage.MyPageKeywordAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupKeywordFragment :
    BaseFragment<FragmentSignupKeywordBinding>(R.layout.fragment_signup_keyword) {

    private val keywordViewModel: SignupKeywordViewModel by viewModels()

    private lateinit var recommendKeywordAdapter: MyPageEditKeywordAdapter
    private lateinit var selectedKeywordAdapter: MyPageKeywordAdapter

    override fun setup() {
        setKeywordAdapter()
        setupStartButtonClickListener()
        setViewModel()

        keywordViewModel.loadRecommendTags()
    }

    private fun setKeywordAdapter() {
        recommendKeywordAdapter = MyPageEditKeywordAdapter(::onClickKeyword)
        binding?.signupRecommendList?.adapter = recommendKeywordAdapter

        selectedKeywordAdapter = MyPageKeywordAdapter()
        binding?.signupSelectedList?.adapter = selectedKeywordAdapter
    }

    private fun onClickKeyword(keyword: String) {
        keywordViewModel.updateKeywordSelectedState(keyword)
    }

    private fun setupStartButtonClickListener() {
        binding?.signupStartBtn?.setOnClickListener {
            keywordViewModel.updateMyKeyword()
            findNavController().let { controller ->
                if (controller.currentDestination?.id == R.id.signupKeywordFragment) {
                    controller.navigate(R.id.action_signupKeywordFragment_to_homeFragment)
                }
            }
        }
    }

    private fun setViewModel() {
        keywordViewModel.keywords.observe(viewLifecycleOwner) { keywords ->
            recommendKeywordAdapter.submitList(keywords)
            selectedKeywordAdapter.submitList(keywords.filter { (_, isSelected) -> isSelected }
                .map { it.first })
        }
    }
}
