package com.example.m3basicsample.core

import android.app.LocaleManager
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@TargetApi(Build.VERSION_CODES.TIRAMISU)
internal class ApplicationLocalesHandler constructor(
    val localeManager: LocaleManager,
) {
}