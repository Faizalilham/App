package com.faizal.newsapp.features.home.home.data.service

import com.faizal.newsapp.common.wrapped.ListResponse
import com.faizal.newsapp.common.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String,
    ): ListResponse<Article>

}