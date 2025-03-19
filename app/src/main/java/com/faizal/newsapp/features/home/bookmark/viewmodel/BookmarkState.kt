package com.faizal.newsapp.features.home.bookmark.viewmodel

import com.faizal.newsapp.common.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)