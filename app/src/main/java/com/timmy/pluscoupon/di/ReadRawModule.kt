package com.timmy.pluscoupon.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ReadRawModule {

    @Provides
    @Singleton
    fun provideRaw(application: Application): CheckDataDao {
        return CheckDataDao(application)
    }

}