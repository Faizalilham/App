package com.faizal.newsapp.features.home.home.viewmodel.detail


import com.faizal.core.domain.model.Article

sealed class DetailEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailEvent()

    object RemoveSideEffect : DetailEvent()

}