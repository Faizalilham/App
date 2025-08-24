package com.faizal.core.domain.usecases.news

import com.faizal.core.domain.repository.NewsRepository
import javax.inject.Inject

class SetFavoriteArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String, isFavorite: Boolean) {
        newsRepository.setFavorite(url, isFavorite)
    }
}