package com.faizal.newsapp.features.home.home.di

import com.faizal.newsapp.features.home.home.data.repository.NewsRepositoryImpl
import com.faizal.newsapp.features.home.home.data.service.NewsApi
import com.faizal.newsapp.features.home.home.domain.repository.NewsRepository
import com.faizal.newsapp.features.home.home.domain.usecase.GetNews
import com.faizal.newsapp.features.home.home.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(getNews = GetNews(newsRepository))
    }
}