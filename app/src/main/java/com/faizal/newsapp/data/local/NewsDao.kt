package com.faizal.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizal.newsapp.data.local.dto.ArticlesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM article WHERE isFavorite = 0 ORDER BY publishedAt DESC")
    fun getArticles(): Flow<List<ArticlesEntity>>

    @Query("SELECT * FROM article WHERE isFavorite = 1 ORDER BY publishedAt DESC")
    fun getFavoriteArticles(): Flow<List<ArticlesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticles(articles: List<ArticlesEntity>)

    @Query("UPDATE article SET isFavorite = :isFavorite WHERE url = :url")
    suspend fun updateFavoriteStatus(url: String, isFavorite: Boolean)

    @Query("SELECT EXISTS(SELECT 1 FROM article WHERE url = :url AND isFavorite = 1)")
    suspend fun isArticleFavorite(url: String): Boolean
}
