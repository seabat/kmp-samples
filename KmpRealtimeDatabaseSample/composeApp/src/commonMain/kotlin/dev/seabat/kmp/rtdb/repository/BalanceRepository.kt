package dev.seabat.kmp.rtdb.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class BalanceRepository(
    private val dataStore: DataStore<Preferences>
) {

    private val balanceKey = stringPreferencesKey("balance")

    suspend fun save(balance: String) {
        dataStore.edit {
            it[balanceKey] = balance
        }
    }

    suspend fun load(): String {
        val preferences = dataStore.data.first()
        val balance = preferences[balanceKey] ?: ""
        return balance
    }
}