package com.aytachuseynli.rickandmortyapp.data.entity

import java.io.Serializable

data class ResultData(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
) : Serializable