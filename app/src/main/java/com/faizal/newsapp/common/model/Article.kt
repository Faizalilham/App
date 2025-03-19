package com.faizal.newsapp.common.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val title : String,
    val source : Source,
    val author : String,
    val description : String,
    @PrimaryKey val url : String,
    val urlToImage : String,
    val publishedAt : String,
    val content : String
):Parcelable
