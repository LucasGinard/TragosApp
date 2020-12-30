package com.example.tragosapp.di

import android.content.Context
import androidx.room.Room
import com.example.tragosapp.appDataBase
import com.example.tragosapp.domain.webService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(webService::class.java)
}