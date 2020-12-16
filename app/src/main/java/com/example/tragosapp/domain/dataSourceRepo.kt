package com.example.tragosapp.domain

import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.vo.Resource

interface dataSourceRepo {
    suspend fun getTragobyNombre(tragoNombre:String): Resource<List<Trago>>
    suspend fun insertTragoFav(tragosEntity: TragosEntity)
    suspend fun getTragosFavRoom(): Resource<List<TragosEntity>>
    suspend fun deleteTrago(trago: TragosEntity)
}