package com.faizal.newsapp.features.home.bookmark.domain.repository

import com.faizal.newsapp.common.model.Article
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

}