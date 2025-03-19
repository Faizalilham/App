package com.faizal.newsapp.features.home.home.domain.usecase

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source : List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(source)
    }
}