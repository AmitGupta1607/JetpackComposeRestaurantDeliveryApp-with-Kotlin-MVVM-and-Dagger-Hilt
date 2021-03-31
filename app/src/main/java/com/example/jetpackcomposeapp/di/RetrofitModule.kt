package com.example.jetpackcomposeapp.di


import android.app.Application
import com.example.jetpackcomposeapp.data.UrlConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    fun providesRetrofit():Retrofit{
       return Retrofit.Builder()
            .baseUrl(UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}