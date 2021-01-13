package com.money.management.prefsstore

import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.money.management.MyApplication
import com.money.management.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreRepository {

    private val dataStore = MyApplication.applicationContext().createDataStore(
            name = Constants.STORE_NAME,
            migrations = listOf(SharedPreferencesMigration(MyApplication.applicationContext(), Constants.PREFS_NAME))
    )

    fun isNightMode(): Flow<Boolean> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.NIGHT_MODE_KEY] ?: false }

    suspend fun toggleNightMode() {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE_KEY = preferencesKey<Boolean>("dark_theme_enabled")
    }

    companion object {
        @Volatile
        private var INSTANCE: DataStoreRepository? = null

        fun getInstance(): DataStoreRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = DataStoreRepository()
                INSTANCE = instance
                instance
            }
        }
    }
}