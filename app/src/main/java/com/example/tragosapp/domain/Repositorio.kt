package com.example.tragosapp.domain

import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.vo.Resource

interface Repositorio {
    suspend fun getTragosList(tragoNombre:String):Resource<List<Trago>>
    suspend fun getTragosFav():Resource<List<TragosEntity>>
    suspend fun insertTrago(trago: TragosEntity)
    suspend fun deleteTrago(trago: TragosEntity)
}