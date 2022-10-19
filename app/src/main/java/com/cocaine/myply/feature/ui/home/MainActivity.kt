package com.cocaine.myply.feature.ui.home

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseActivity
import com.cocaine.myply.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun setup() {
        setViewModel()

        viewModel.getUserRegisterState()
    }

    fun showBottomNav() {
        binding?.mainBottomMenu?.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding?.mainBottomMenu?.visibility = View.GONE
    }

    private fun setViewModel() {
        viewModel.isUserRegistered.observe(this) {
            setNavGraph(it)
            setupNavController()
        }
    }

    private fun setNavGraph(isUserRegistered: Boolean) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navHostFragment.navController.graph =
            navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph).apply {
                setStartDestination(
                    when (isUserRegistered) {
                        true -> R.id.homeFragment
                        false -> R.id.onBoardingFragment
                    }
                )
            }
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.mainBottomMenu?.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment, R.id.searchFragment, R.id.myPageFragment, R.id.keepFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }
}
