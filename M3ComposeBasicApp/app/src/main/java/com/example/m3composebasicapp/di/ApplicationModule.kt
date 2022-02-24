package com.example.m3composebasicapp.di

import android.app.LocaleManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    internal fun provideLocaleManager(@ApplicationContext context: Context): LocaleManager {
        return context.getSystemService(LocaleManager::class.java)
    }
}