package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseAppCheckDataSourceContract
import dev.seabat.kmp.firebasestorage.result.FirebaseAppCheckResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppCheckRepository : KoinComponent {
    fun activate() {
        val firebaseAppCheckDataSource: FirebaseAppCheckDataSourceContract by inject()
        firebaseAppCheckDataSource.activate { result ->
            if (result is FirebaseAppCheckResult.Error) {
                println("Fail to activate AppCheck: ${result.error.message}")
            }
        }
    }
}