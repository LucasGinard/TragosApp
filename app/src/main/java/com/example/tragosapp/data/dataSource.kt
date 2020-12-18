package com.example.tragosapp.data

import com.example.tragosapp.appDataBase
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.dataSourceRepo
import com.example.tragosapp.domain.tragoDao
import com.example.tragosapp.vo.Resource
import com.example.tragosapp.vo.retrofitClient
import javax.inject.Inject

class dataSource @Inject constructor(private val tragodao: tragoDao):dataSourceRepo{


    override suspend fun getTragobyNombre(tragoNombre:String):Resource<List<Trago>>{
        return Resource.Sucess(retrofitClient.webservice.getTragoNombre(tragoNombre).listtrago)
    }

    override suspend fun insertTragoFav(tragosEntity: TragosEntity){
        tragodao.insertFavTragos(tragosEntity)
    }

    override suspend fun getTragosFavRoom(): Resource<List<TragosEntity>> {
        return Resource.Sucess(tragodao.getFavsTragos())
    }

    override suspend fun deleteTrago(trago: TragosEntity) {
        tragodao.deleteTrago(trago)
    }
}