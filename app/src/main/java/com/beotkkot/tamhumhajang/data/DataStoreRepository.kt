package com.beotkkot.tamhumhajang.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreRepository(
    private val preferenceDataStore: DataStore<Preferences>
) {

    fun getIntValue(type: Preferences.Key<Int>): Flow<Int> {
        return preferenceDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { prefs ->
                prefs[type] ?: 0
            }
    }

    suspend fun setIntValue(type: Preferences.Key<Int>, value: Int) {
        preferenceDataStore.edit { settings ->
            settings[type] = value
        }
    }

    fun getBooleanValue(type: Preferences.Key<Boolean>): Flow<Boolean> {
        return preferenceDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { prefs ->
                prefs[type] ?: false
            }
    }

    suspend fun setBooleanValue(type: Preferences.Key<Boolean>, value: Boolean) {
        preferenceDataStore.edit { settings ->
            settings[type] = value
        }
    }

}