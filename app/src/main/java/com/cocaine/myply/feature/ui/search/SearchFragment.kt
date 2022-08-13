package com.cocaine.myply.feature.ui.search

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override fun setup() {
        binding?.searchEditTxt?.setOnFocusChangeListener { view, isFocus ->
            if (isFocus) {
                binding?.searchBtn?.visibility = View.VISIBLE
            } else {
                binding?.searchBtn?.visibility = View.INVISIBLE
            }
        }
    }
}