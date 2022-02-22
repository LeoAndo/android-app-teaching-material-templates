package com.example.m3basicsample.core

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@TargetApi(Build.VERSION_CODES.TIRAMISU)
internal class ApplicationLocalesHandler constructor(
    val localeManager: LocaleManager,
) {
    inline val supportLanguageTags
        get() = localeManager.applicationLocales.toLanguageTags()

    inline var applicationLocales: LocaleList
        get() = localeManager.applicationLocales
        set(locales) {
            localeManager.applicationLocales = locales
        }

    companion object {
        private const val TAG = "ApplicationLocalesHandler"
    }
}