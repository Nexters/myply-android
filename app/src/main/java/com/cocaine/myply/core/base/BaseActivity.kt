package com.cocaine.myply.core.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity<T : ViewDataBinding>(private val resId: Int) : FragmentActivity() {
    var binding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resId)
        binding?.lifecycleOwner = this

        setup()
    }

    abstract fun setup()
}