package com.cocaine.myply.feature.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseActivity
import com.cocaine.myply.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun setup() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.mainBottomMenu?.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.keepDetailFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    fun showBottomNav() {
        binding?.mainBottomMenu?.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding?.mainBottomMenu?.visibility = View.GONE
    }
}