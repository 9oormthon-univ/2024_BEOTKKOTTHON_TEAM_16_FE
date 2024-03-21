package com.beotkkot.tamhumhajang.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.beotkkot.tamhumhajang.data.ApiDataSource
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.ApiService
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesApiDataSource(
        apiService: ApiService
    ) = ApiDataSource(apiService)

    @Provides
    @Singleton
    fun providesApiRepository(
        apiDataSource: ApiDataSource
    ) = ApiRepository(apiDataSource)

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        pref: DataStore<Preferences>
    ) = DataStoreRepository(pref)
}