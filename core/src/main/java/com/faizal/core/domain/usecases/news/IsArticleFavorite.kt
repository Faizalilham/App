package com.faizal.core.domain.usecases.news

import com.faizal.core.domain.repository.NewsRepository
import javax.inject.Inject

class IsArticleFavorite @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return newsRepository.isArticleFavorite(url)
    }
}