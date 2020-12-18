package com.example.tragosapp.di

import com.example.tragosapp.data.dataSource
import com.example.tragosapp.domain.RepoImplement
import com.example.tragosapp.domain.Repositorio
import com.example.tragosapp.domain.dataSourceRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class activityModule {

    @Binds
    abstract fun bindRepoImplement(repoImplement: RepoImplement):Repositorio

    @Binds
    abstract fun dataSourceRepoImplement(dataSource: dataSource):dataSourceRepo
}