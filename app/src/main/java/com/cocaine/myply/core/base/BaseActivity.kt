package com.cocaine.myply.core.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.cocaine.myply.R

abstract class BaseActivity<T : ViewDataBinding>(private val resId: Int) : FragmentActivity() {
    var binding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyPly)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, resId)
        binding?.lifecycleOwner = this

        setup()
    }

    abstract fun setup()
}