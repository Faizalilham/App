package com.faizal.newsapp.features.home.home.domain.repository

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources : List<String>): Flow<PagingData<Article>>

}