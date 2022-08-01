package com.cocaine.myply.feature.ui.search

import android.view.View
import android.view.ViewTreeObserver
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentSearchBinding

class SearchFragment: BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override fun setup() {
        binding?.searchEditText?.setOnFocusChangeListener { view, isFocus ->
            if(isFocus) {
                binding?.searchBtn?.visibility = View.VISIBLE
            } else {
                binding?.searchBtn?.visibility = View.INVISIBLE
            }
        }
    }
}