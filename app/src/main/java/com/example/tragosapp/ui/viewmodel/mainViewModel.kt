package com.example.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.Repositorio
import com.example.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class mainViewModel(private val repositorio: Repositorio):ViewModel(){

    private val tragosdata = MutableLiveData<String>()

    fun setTrago(tragoNombre:String){
        tragosdata.value = tragoNombre
    }

    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosdata.distinctUntilChanged().switchMap {nombreTrago ->
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try{
                emit(repositorio.getTragosList(nombreTrago))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveTragoFav(trago:TragosEntity){
        viewModelScope.launch {
            repositorio.insertTrago(trago)
        }
    }

    fun getTragosFav() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(repositorio.getTragosFav())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteTragos(trago: TragosEntity){
        viewModelScope.launch {
            repositorio.deleteTrago(trago)
        }
    }
}