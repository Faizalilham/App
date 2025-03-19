package com.faizal.newsapp.features.home.search.data.service


import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.common.wrapped.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String,
    ): ListResponse<Article>
}