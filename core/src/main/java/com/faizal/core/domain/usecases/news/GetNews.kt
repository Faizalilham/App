package com.faizal.core.domain.usecases.news

import androidx.paging.PagingData
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source : List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(source)
    }
}