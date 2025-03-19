package com.faizal.newsapp.features.home.bookmark.domain.usecase


data class BookmarkUseCase(
    val upsertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)