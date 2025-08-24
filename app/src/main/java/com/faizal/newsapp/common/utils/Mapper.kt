package com.faizal.newsapp.common.utils

import com.faizal.newsapp.data.local.dto.ArticlesEntity
import com.faizal.newsapp.domain.model.Article
import com.faizal.newsapp.domain.model.Source

object Mapper {

    fun Article.toEntity() = ArticlesEntity(
        url = url,
        title = title,
        sourceName = source.name,
        sourceId = source.id,
        author = author,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isFavorite = isFavorite
    )

    fun ArticlesEntity.toDomain() = Article(
        url = url,
        title = title,
        source = Source(id = sourceId, name = sourceName ?: "-"),
        author = author ?: "-",
        description = description ?: "-",
        urlToImage = urlToImage ?: "-",
        publishedAt = publishedAt,
        content = content ?: "-",
        isFavorite = isFavorite
    )

    fun List<ArticlesEntity>.toDomain() = map { it.toDomain() }
    fun List<Article>.toEntity() = map { it.toEntity() }
}