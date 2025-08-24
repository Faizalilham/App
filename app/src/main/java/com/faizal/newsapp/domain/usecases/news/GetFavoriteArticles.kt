package com.faizal.newsapp.domain.usecases.news

import com.faizal.newsapp.domain.model.Article
import com.faizal.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getFavoriteArticles()
    }
}