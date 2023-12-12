package com.aytachuseynli.rickandmortyapp.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(directions: NavDirections) {
    try {
        this.navigate(directions = directions)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}