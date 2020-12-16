package com.example.tragosapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tragosapp.domain.Repositorio

class vmFactory(private val repositorio: Repositorio):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repositorio::class.java).newInstance(repositorio)
    }

}