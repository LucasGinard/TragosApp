package com.example.tragosapp.domain

import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.vo.Resource
import javax.inject.Inject

class RepoImplement @Inject constructor(private val dataSource: dataSourceRepo):Repositorio {
    override suspend fun getTragosList(tragoNombre:String): Resource<List<Trago>> {
        return dataSource.getTragobyNombre(tragoNombre)
    }

    override suspend fun getTragosFav(): Resource<List<TragosEntity>> {
        return dataSource.getTragosFavRoom()
    }

    override suspend fun insertTrago(trago: TragosEntity) {
        dataSource.insertTragoFav(trago)
    }

    override suspend fun deleteTrago(trago: TragosEntity) {
        dataSource.deleteTrago(trago)
    }
}