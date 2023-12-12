package com.aytachuseynli.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.data.repo.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characters = MutableLiveData<PagingData<ResultData>>()
    val characters: LiveData<PagingData<ResultData>> get() = _characters

    fun getCharacters() {
        viewModelScope.launch {
            try {
                characterRepository.getCharacters().cachedIn(viewModelScope).collectLatest {
                    _characters.value = it
                }

            } catch (e: Exception) {
            }
        }
    }
}