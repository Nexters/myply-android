package com.cocaine.myply.feature.ui.home

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseActivity
import com.cocaine.myply.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun setup() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.mainBottomMenu?.setupWithNavController(navController)
    }
}