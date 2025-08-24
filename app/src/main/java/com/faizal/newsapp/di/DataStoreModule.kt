package com.faizal.newsapp.di

import com.faizal.newsapp.data.datastore.LocalUserManagerImpl
import com.faizal.newsapp.domain.datasource.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManger(localUserMangerImpl: LocalUserManagerImpl) : LocalUserManager
}