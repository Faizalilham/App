package com.faizal.newsapp.features.home.search.domain.repository

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getSearchNews(query : String, sources : List<String>): Flow<PagingData<Article>>
}