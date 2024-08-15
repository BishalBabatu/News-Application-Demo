package com.example.newbees.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import com.mubarak.newscastmb.R
import com.mubarak.newscastmb.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_host) as NavHostFragment
        val navController = navHostFragment.navController

        // Initially hide the bottom navigation view
        binding.btnView.visibility = View.GONE

        // Setup NavController with BottomNavigationView
        binding.btnView.setupWithNavController(navController)

        // Observe the navigation controller's destination changes
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.passwordResetFragment -> binding.btnView.visibility = View.GONE
                else -> binding.btnView.visibility = View.VISIBLE
            }
        }
    }
}
