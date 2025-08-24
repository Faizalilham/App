package com.faizal.newsapp.data.remote.wrapped



data class SingleResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : T
)
