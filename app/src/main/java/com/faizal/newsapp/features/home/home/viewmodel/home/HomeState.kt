package com.faizal.newsapp.features.home.home.viewmodel.home

import com.faizal.core.domain.model.Article


data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val articlesOffline: List<Article> = emptyList(),
    val isConnected: Boolean = false,
    val error: String? = null
)