package com.faizal.newsapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticlesEntity(
    @PrimaryKey val url: String,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val sourceName: String?,
    val sourceId: String?,
    val page: Int = 0,
    var isFavorite : Boolean = false
)