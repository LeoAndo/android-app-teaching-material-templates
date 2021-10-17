package com.example.basicappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicappsample.databinding.ActivityMainBinding
import com.example.basicappsample.helpers.AnimationHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.textHello.text = "Hello, Android Kotlin"
        AnimationHelper.startAlphaAnimation(binding.textHello)
    }
}