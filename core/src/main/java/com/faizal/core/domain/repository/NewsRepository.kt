package com.faizal.core.domain.repository

import androidx.paging.PagingData
import com.faizal.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    fun getArticles(): Flow<List<Article>>

    fun getFavoriteArticles(): Flow<List<Article>>

    suspend fun setFavorite(url: String, isFavorite: Boolean)

    suspend fun isArticleFavorite(url: String): Boolean

}