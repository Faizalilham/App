package com.faizal.core.utils

import com.faizal.core.data.local.dto.ArticlesEntity
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.model.Source

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
}