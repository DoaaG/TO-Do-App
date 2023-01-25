package com.example.todoapplication.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todoapplication.databinding.ActivitySplashScreenBinding
import com.example.todoapplication.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({startSplash()},2000)
//        {startSplash()} lambda ex  interface with one function

    }

    private fun startSplash() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()   // when press back don't return to the splash
    }
}