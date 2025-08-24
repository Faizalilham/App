package com.faizal.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.faizal.core.data.local.NewsDao
import com.faizal.core.data.remote.NewsApi
import com.faizal.core.data.remote.NewsPagingSource
import com.faizal.core.data.remote.SearchNewsPagingSource
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.repository.NewsRepository
import com.faizal.core.utils.Mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi,newsDao = newsDao, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getFavoriteArticles(): Flow<List<Article>> {
        return newsDao.getFavoriteArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun setFavorite(url: String, isFavorite: Boolean) {
        newsDao.updateFavoriteStatus(url, isFavorite)
    }

    override suspend fun isArticleFavorite(url: String): Boolean {
        return newsDao.isArticleFavorite(url)
    }
}
