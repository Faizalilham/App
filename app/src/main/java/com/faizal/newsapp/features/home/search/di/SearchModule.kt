package com.faizal.newsapp.features.home.search.di

import com.faizal.newsapp.features.home.search.data.repository.SearchRepositoryImpl
import com.faizal.newsapp.features.home.search.data.service.SearchService
import com.faizal.newsapp.features.home.search.domain.repository.SearchRepository
import com.faizal.newsapp.features.home.search.domain.usecase.SearchNews
import com.faizal.newsapp.features.home.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchRepository(searchService: SearchService): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase {
        return SearchUseCase(searchNews = SearchNews(searchRepository))
    }
}