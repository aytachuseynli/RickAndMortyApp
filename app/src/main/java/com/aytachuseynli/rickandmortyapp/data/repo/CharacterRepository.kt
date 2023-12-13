package com.aytachuseynli.rickandmortyapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.data.remote.CharacterApi
import com.aytachuseynli.rickandmortyapp.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val characterApi: CharacterApi ) {
    fun getFilteredCharacter(
        name :String = "" ,
        gender:String ="" ,
        status :String="" ,
        species: String=""
    ):Flow<PagingData<ResultData>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50
            ),
            pagingSourceFactory = { CharacterPagingSource(characterApi , name , gender, status, species) }
        ).flow
    }

}