package com.faizal.newsapp.features.home.bookmark.domain.usecase


import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.bookmark.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val bookmarkRepository: BookmarkRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return bookmarkRepository.getArticles()
    }

}