package com.faizal.newsapp.features.home.search.domain.usecase

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return searchRepository.getSearchNews(
            query = searchQuery,
            sources = sources
        )
    }
}