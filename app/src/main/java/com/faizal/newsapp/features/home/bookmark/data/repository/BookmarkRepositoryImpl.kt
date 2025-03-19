package com.faizal.newsapp.features.home.bookmark.data.repository

import com.faizal.newsapp.common.database.NewsDao
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.bookmark.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(
    private val newsDao: NewsDao
) : BookmarkRepository {

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}