package com.faizal.newsapp.features.home.bookmark.di


import com.faizal.newsapp.common.database.NewsDao
import com.faizal.newsapp.features.home.bookmark.data.repository.BookmarkRepositoryImpl
import com.faizal.newsapp.features.home.bookmark.domain.repository.BookmarkRepository
import com.faizal.newsapp.features.home.bookmark.domain.usecase.BookmarkUseCase
import com.faizal.newsapp.features.home.bookmark.domain.usecase.DeleteArticle
import com.faizal.newsapp.features.home.bookmark.domain.usecase.GetArticle
import com.faizal.newsapp.features.home.bookmark.domain.usecase.GetArticles
import com.faizal.newsapp.features.home.bookmark.domain.usecase.InsertArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BookmarkModule {

    @Provides
    @Singleton
    fun provideSearchRepository(newsDao: NewsDao): BookmarkRepository {
        return BookmarkRepositoryImpl(newsDao)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(bookmarkRepository: BookmarkRepository): BookmarkUseCase {
        return BookmarkUseCase(
            upsertArticle = InsertArticle(bookmarkRepository),
            deleteArticle = DeleteArticle(bookmarkRepository),
            getArticle = GetArticle(bookmarkRepository),
            getArticles = GetArticles(bookmarkRepository),
        )
    }
}