package com.example.tragosapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.tragoDao

@Database(entities = arrayOf(TragosEntity::class),version = 1)
abstract class appDataBase: RoomDatabase() {
    abstract fun tragoDao():tragoDao
}