package com.example.borutoapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.borutoapp.domain.manager.LocalManager
import com.example.borutoapp.utils.Constants.PREFERENCES_KEY
import com.example.borutoapp.utils.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// create extension variable will be used to access the Data Store
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class LocalManagerImpl(context: Context) : LocalManager {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        /*
        to save a simple value in to our dataStore preference,
        by call the datastore.edit which persist that value by using the key
         */
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        // read the value from saveOnBoardingState() key
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}