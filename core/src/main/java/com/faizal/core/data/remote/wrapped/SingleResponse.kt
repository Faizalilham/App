package com.faizal.core.data.remote.wrapped



data class SingleResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : T
)
