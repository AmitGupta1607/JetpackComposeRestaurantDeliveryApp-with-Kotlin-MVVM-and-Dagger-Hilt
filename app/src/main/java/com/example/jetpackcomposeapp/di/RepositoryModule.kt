package com.example.jetpackcomposeapp.di

import com.example.jetpackcomposeapp.data.repository.Repository
import com.example.jetpackcomposeapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{


    @Singleton
    @Provides
    fun getRepository(retrofit: Retrofit): Repository {
        return RepositoryImpl(retrofit = retrofit)
    }

}