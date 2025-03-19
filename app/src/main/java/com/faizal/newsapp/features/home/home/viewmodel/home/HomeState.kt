package com.faizal.newsapp.features.home.home.viewmodel.home

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)