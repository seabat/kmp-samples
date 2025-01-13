package dev.seabat.kmp.rtdb.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class GuidRepository(
    private val dataStore: DataStore<Preferences>
) {

    private val guidKey = stringPreferencesKey("guid")

    suspend fun save(guid: String) {
        dataStore.edit {
            it[guidKey] = guid
        }
    }

    suspend fun load(): String {
        val preferences = dataStore.data.first()
        return preferences[guidKey] ?: ""
    }
}