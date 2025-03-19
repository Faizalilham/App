package com.faizal.newsapp.common.wrapped



data class ListResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : List<T>
)
