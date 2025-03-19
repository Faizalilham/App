package com.faizal.newsapp.features.home.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.search.data.service.SearchService
import com.faizal.newsapp.features.home.search.data.source.SearchPagingDataSource
import com.faizal.newsapp.features.home.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val searchService: SearchService
) : SearchRepository {

    override fun getSearchNews(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingDataSource(
                    searchService,
                    query,
                    sources.joinToString(separator = ","))
            }
        ).flow
    }
}