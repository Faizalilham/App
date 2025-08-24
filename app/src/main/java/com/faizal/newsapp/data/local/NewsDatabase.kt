package com.faizal.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faizal.newsapp.data.local.dto.ArticlesEntity

@Database(
    entities = [ArticlesEntity::class],
    version = 6,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}