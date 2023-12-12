package com.aytachuseynli.rickandmortyapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.data.remote.CharacterApi
import java.io.IOException


class CharacterPagingSource(private val characterApi: CharacterApi) : PagingSource<Int, ResultData>() {
    override fun getRefreshKey(state: PagingState<Int, ResultData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = characterApi.getAll(page = nextPageNumber).body()
            println(nextPageNumber)

            if (response != null) {
                LoadResult.Page(
                    data = response.results ?: emptyList(),
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (response.results.isNullOrEmpty()) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(Exception("Empty response"))
            }


        } catch (e: IOException) {
            LoadResult.Error(e)
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}