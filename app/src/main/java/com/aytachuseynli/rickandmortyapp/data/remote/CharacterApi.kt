package com.aytachuseynli.rickandmortyapp.data.remote

import com.aytachuseynli.rickandmortyapp.data.entity.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterApi {
    @GET("character")
    suspend fun getAll(
        @Query("page")page:Int,
        @Query("name")name : String = "",
        @Query("status") status: String = "",
        @Query("gender") gender: String = "",
        @Query("species") species: String = ""
    ): Response<Characters>
}