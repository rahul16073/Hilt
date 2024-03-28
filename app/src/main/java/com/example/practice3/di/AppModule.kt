package com.example.practice3.di

import com.example.practice3.data.remote.Api
import com.example.practice3.data.repository.RepositoryImpl
import com.example.practice3.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInstance(): Api {
        return Retrofit.Builder().baseUrl("https://api.publicapis.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: Api): Repository{
        return RepositoryImpl(api)
    }
}