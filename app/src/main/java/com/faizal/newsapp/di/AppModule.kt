package com.faizal.newsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.faizal.newsapp.common.utils.Constant.BASE_URL
import com.faizal.newsapp.common.utils.NetworkConnectionManager
import com.faizal.newsapp.data.local.NewsDao
import com.faizal.newsapp.data.local.NewsDatabase
import com.faizal.newsapp.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionManager(
        @ApplicationContext context: Context
    ): NetworkConnectionManager {
        return NetworkConnectionManager(context)
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
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}