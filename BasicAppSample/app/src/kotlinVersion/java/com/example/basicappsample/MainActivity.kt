package com.example.basicappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.example.basicappsample.databinding.ActivityMainBinding
import com.example.basicappsample.helpers.AnimationHelper
import com.example.basicappsample.helpers.KeyboardHelpable

class MainActivity : AppCompatActivity(), KeyboardHelpable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.textHello.text = "Hello, Android Kotlin"
        AnimationHelper.startAlphaAnimation(binding.textHello)

        binding.editEmail.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                return@setOnKeyListener true
            } else {
                return@setOnKeyListener false
            }
        }
    }
}