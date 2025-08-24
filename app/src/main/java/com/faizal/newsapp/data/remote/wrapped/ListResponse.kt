package com.faizal.newsapp.data.remote.wrapped



data class ListResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : List<T>
)
