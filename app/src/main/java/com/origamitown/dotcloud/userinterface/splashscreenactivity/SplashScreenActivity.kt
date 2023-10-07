package com.origamitown.dotcloud.userinterface.splashscreenactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.ActivitySplashScreenBinding
import com.origamitown.dotcloud.userinterface.mainactivity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            val mainActivityIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }
    }
}