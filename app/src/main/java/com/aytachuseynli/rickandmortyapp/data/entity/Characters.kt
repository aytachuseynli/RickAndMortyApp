package com.aytachuseynli.rickandmortyapp.data.entity

import java.io.Serializable


data class Characters(
    val info: Info,
    val results: List<ResultData>
):Serializable
