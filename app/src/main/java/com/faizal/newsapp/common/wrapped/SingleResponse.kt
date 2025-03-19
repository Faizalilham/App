package com.faizal.newsapp.common.wrapped

import com.faizal.newsapp.common.model.Article

data class SingleResponse<T>(
    val status : String,
    val totalResults : Int,
    val articles : T
)
