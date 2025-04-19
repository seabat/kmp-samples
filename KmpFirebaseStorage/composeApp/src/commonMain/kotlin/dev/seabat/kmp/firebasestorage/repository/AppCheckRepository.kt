package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseAppCheckDataSourceContract
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppCheckRepository : KoinComponent {
    fun activate() {
        val firebaseAppCheckDataSource: FirebaseAppCheckDataSourceContract by inject()
        firebaseAppCheckDataSource.activate { result, throwable ->
            throwable?.let {
                println("Fail to activate AppCheck: ${throwable.message}")
            }
        }
    }
}