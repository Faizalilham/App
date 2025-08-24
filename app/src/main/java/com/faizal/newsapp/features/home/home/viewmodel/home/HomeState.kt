package com.faizal.newsapp.features.home.home.viewmodel.home

import com.faizal.newsapp.domain.model.Article

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val articlesOffline: List<Article> = emptyList()
)