package com.mohamedabdelaziz.jahez.core.di

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.app.Application
import com.mohamedabdelaziz.jahez.data.datasource.local.dp.RestaurantDataBase
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
        @Provides
        @Singleton
        fun provideDB(application: Application): RestaurantDataBase = Room.databaseBuilder(application, RestaurantDataBase::class.java, "restaurant_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
}