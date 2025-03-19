package com.faizal.newsapp.di


import android.app.Application
import androidx.room.Room
import com.faizal.newsapp.common.database.NewsDao
import com.faizal.newsapp.common.database.NewsDatabase
import com.faizal.newsapp.common.database.NewsTypeConvertor
import com.faizal.newsapp.datastore.domain.LocalUserManager
import com.faizal.newsapp.datastore.data.LocalUserManagerImpl
import com.faizal.newsapp.datastore.domain.AppEntryUseCases
import com.faizal.newsapp.datastore.domain.ReadAppEntry
import com.faizal.newsapp.datastore.domain.SaveAppEntry
import com.faizal.newsapp.features.home.home.data.repository.NewsRepositoryImpl
import com.faizal.newsapp.features.home.home.data.service.NewsApi
import com.faizal.newsapp.features.home.home.domain.repository.NewsRepository
import com.faizal.newsapp.features.home.home.domain.usecase.GetNews
import com.faizal.newsapp.features.home.home.domain.usecase.NewsUseCase
import com.faizal.newsapp.common.utils.Constant
import com.faizal.newsapp.features.home.search.data.repository.SearchRepositoryImpl
import com.faizal.newsapp.features.home.search.data.service.SearchService
import com.faizal.newsapp.features.home.search.domain.repository.SearchRepository
import com.faizal.newsapp.features.home.search.domain.usecase.SearchNews
import com.faizal.newsapp.features.home.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}