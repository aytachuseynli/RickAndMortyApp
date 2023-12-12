package com.aytachuseynli.rickandmortyapp.di

import com.aytachuseynli.rickandmortyapp.data.remote.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit() =
        Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(CharacterApi::class.java)

}