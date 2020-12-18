package com.example.tragosapp.di

import android.content.Context
import androidx.room.Room
import com.example.tragosapp.appDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object appModule {

    @Singleton
    @Provides
    fun provideRoomInstancia(@ApplicationContext context:Context) = Room.databaseBuilder(
        context,
        appDataBase::class.java,
        "table_tragos")
        .build()

    @Provides
    @Singleton
    fun provideTragoDao(db:appDataBase) =   db.tragoDao()

}