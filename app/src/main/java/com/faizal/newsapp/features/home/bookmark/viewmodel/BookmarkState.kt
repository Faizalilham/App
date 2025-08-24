package com.faizal.newsapp.features.home.bookmark.viewmodel


import com.faizal.core.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)