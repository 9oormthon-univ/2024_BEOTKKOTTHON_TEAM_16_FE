package com.beotkkot.tamhumhajang.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    const val TAMHUMHAJANG_DATSTORE = "tamhumhajang_datastore"

    // PreferenceKeys
    val IS_NOT_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    val USER_ID = intPreferencesKey("user_id")
    val GRADE = intPreferencesKey("grade")
    val SEQUENCE = intPreferencesKey("sequence")


    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(TAMHUMHAJANG_DATSTORE) },
        )
}