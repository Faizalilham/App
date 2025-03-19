package com.faizal.newsapp.features.home.bookmark.domain.usecase


import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.bookmark.domain.repository.BookmarkRepository

class GetArticle (
    private val bookmarkRepository: BookmarkRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return bookmarkRepository.getArticle(url = url)
    }

}