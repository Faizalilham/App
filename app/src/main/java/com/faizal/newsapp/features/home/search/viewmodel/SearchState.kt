package com.faizal.newsapp.features.home.search.viewmodel

import androidx.paging.PagingData
import com.faizal.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)