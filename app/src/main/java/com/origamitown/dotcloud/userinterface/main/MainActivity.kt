package com.origamitown.dotcloud.userinterface.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.origamitown.dotcloud.databinding.ActivityMainBinding
import com.origamitown.dotcloud.userinterface.main.viewmodels.FeedViewModel

class MainActivity : AppCompatActivity() {

    private val feedViewModel by viewModels<FeedViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup navigation
        val navHost =
            supportFragmentManager.findFragmentById(binding.mainContent.mainContentFragmentContainer.id) as NavHostFragment
        val navController = navHost.navController
        val bottomNav = binding.mainBottomNav
        bottomNav.setupWithNavController(navController)

        binding.mainAddPhotoButton.setOnClickListener {
            feedViewModel.addInitialData()
        }
    }
}