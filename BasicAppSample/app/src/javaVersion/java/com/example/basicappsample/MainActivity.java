package com.example.basicappsample;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivityMainBinding;
import com.example.basicappsample.helpers.AnimationHelper;
import com.example.basicappsample.helpers.KeyboardHelpable;

public class MainActivity extends AppCompatActivity implements KeyboardHelpable {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textHello.setText("Hello, Android Java");
        AnimationHelper.startAlphaAnimation(binding.textHello);
        binding.editEmail.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard(MainActivity.this);
                return true;
            } else {
                return false;
            }
        });
    }
}
