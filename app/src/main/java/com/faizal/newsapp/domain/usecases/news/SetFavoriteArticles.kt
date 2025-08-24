package com.faizal.newsapp.domain.usecases.news

import com.faizal.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SetFavoriteArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String, isFavorite: Boolean) {
        newsRepository.setFavorite(url, isFavorite)
    }
}