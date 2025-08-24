package com.faizal.core.data.remote

import com.faizal.core.data.remote.wrapped.ListResponse
import com.faizal.core.domain.model.Article
import com.faizal.core.utils.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey: String = API_KEY
    ): ListResponse<Article>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): ListResponse<Article>

}