package com.example.m3basicsample.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApplicationModule {
    /*
    @Singleton
    @Provides
    fun provideMyLocaleManager(@ApplicationContext context: Context): LocaleManager {
        return context.getSystemService(LocaleManager::class.java)
    }
     */
}