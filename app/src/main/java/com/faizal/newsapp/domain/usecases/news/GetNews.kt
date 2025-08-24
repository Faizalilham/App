package com.faizal.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.faizal.newsapp.domain.model.Article
import com.faizal.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source : List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(source)
    }
}