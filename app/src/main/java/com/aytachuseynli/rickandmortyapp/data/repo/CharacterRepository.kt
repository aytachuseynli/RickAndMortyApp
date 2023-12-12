package com.aytachuseynli.rickandmortyapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.data.remote.CharacterApi
import com.aytachuseynli.rickandmortyapp.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val characterApi: CharacterApi) {


    fun getCharacters(): Flow<PagingData<ResultData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 200
            ),
            pagingSourceFactory = { CharacterPagingSource(characterApi) }
        ).flow
    }


}