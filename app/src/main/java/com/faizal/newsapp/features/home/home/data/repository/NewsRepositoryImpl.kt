package com.faizal.newsapp.features.home.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.faizal.newsapp.features.home.home.data.service.NewsApi
import com.faizal.newsapp.features.home.home.data.source.NewsPagingDataSource
import com.faizal.newsapp.features.home.search.data.source.SearchPagingDataSource
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingDataSource(
                    newsApi,
                    sources.joinToString(separator = ","))
            }
        ).flow
    }
}