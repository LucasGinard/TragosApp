package com.example.tragosapp.domain

import androidx.room.*
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity

@Dao
interface tragoDao {

    @Query("SELECT  * FROM tragosentity")
    suspend fun getFavsTragos():List<TragosEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTragos(trago: TragosEntity)

    @Delete
    suspend fun deleteTrago(trago:TragosEntity)

}