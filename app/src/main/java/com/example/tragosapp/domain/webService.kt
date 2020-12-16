package com.example.tragosapp.domain

import com.example.tragosapp.data.model.TragoList
import retrofit2.http.GET
import retrofit2.http.Query

interface webService {

    @GET("search.php")
    suspend fun getTragoNombre(@Query("s") tragoNombre:String):TragoList
}