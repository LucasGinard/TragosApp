package com.example.tragosapp.data

import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.dataSourceRepo
import com.example.tragosapp.domain.tragoDao
import com.example.tragosapp.domain.webService
import com.example.tragosapp.vo.Resource
import javax.inject.Inject

class dataSource @Inject constructor(private val tragodao: tragoDao,private val webService: webService):dataSourceRepo{


    override suspend fun getTragobyNombre(tragoNombre:String):Resource<List<Trago>>{
        return Resource.Sucess(webService.getTragoNombre(tragoNombre).listtrago?: listOf())
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