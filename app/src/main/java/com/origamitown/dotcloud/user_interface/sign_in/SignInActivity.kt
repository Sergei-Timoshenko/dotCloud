package com.origamitown.dotcloud.user_interface.sign_in

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.origamitown.dotcloud.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}