package com.example.basicappsample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivityMainBinding;
import com.example.basicappsample.helpers.AnimationHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textHello.setText("Hello, Android Java");
        AnimationHelper.startAlphaAnimation(binding.textHello);
    }
}
