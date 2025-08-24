package com.faizal.core.data.remote.wrapped



data class ListResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : List<T>
)
