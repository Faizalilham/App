package com.faizal.newsapp.features.home.bookmark.domain.usecase


import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.bookmark.domain.repository.BookmarkRepository

class InsertArticle(
    private val bookmarkRepository: BookmarkRepository
) {

    suspend operator fun invoke(article: Article){
        bookmarkRepository.upsertArticle(article = article)
    }
}