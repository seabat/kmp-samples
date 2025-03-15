package dev.seabat.kmp.rtdb.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class UserIdRepository(
    private val dataStore: DataStore<Preferences>
) {

    private val userIdKey = stringPreferencesKey("userid")

    suspend fun save(userId: String) {
        dataStore.edit {
            it[userIdKey] = userId
        }
    }

    suspend fun load(): String {
        val preferences = dataStore.data.first()
        val userId = preferences[userIdKey] ?: ""
        return userId
    }
}