package com.cocaine.myply.feature.ui.mypage

import androidx.fragment.app.activityViewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentMypageEditBinding

class MyPageEditFragment : BaseFragment<FragmentMypageEditBinding>(R.layout.fragment_mypage_edit) {

    private val myPageViewModel: MyPageViewModel by activityViewModels()

    private lateinit var myPageEditKeywordAdapter: MyPageEditKeywordAdapter

    private lateinit var myPageKeywordAdapter: MyPageKeywordAdapter

    override fun setup() {
        setKeywordAdapter()
        setKeywordUpdateConfirmClickListener()
        setKeywordObserver()

        myPageViewModel.loadRecommendTags()
    }

    private fun setKeywordAdapter() {
        myPageEditKeywordAdapter = MyPageEditKeywordAdapter(::onClickKeyword)
        binding?.mypageEditList?.adapter = myPageEditKeywordAdapter

        myPageKeywordAdapter = MyPageKeywordAdapter()
        binding?.mypageEditCheckedList?.adapter = myPageKeywordAdapter
    }

    private fun setKeywordUpdateConfirmClickListener() {
        binding?.mypageEditBtn?.setOnClickListener {
            myPageViewModel.updateKeywords()
        }
    }

    private fun setKeywordObserver() {
        myPageViewModel.baseKeywords.observe(viewLifecycleOwner) {
            myPageEditKeywordAdapter.submitList(it)
        }
        myPageViewModel.clickedKeywords.observe(viewLifecycleOwner) {
            myPageKeywordAdapter.submitList(it)
        }
    }

    private fun onClickKeyword(keyword: String) {
        myPageViewModel.updateKeywordClickStatus(keyword)
    }
}
