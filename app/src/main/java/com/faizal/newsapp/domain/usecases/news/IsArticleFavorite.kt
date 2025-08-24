package com.faizal.newsapp.domain.usecases.news

import com.faizal.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class IsArticleFavorite @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return newsRepository.isArticleFavorite(url)
    }
}