package dev.seabat.kmp.firebasestorage.datasource

import dev.seabat.kmp.firebasestorage.result.FirebaseAppCheckResult

interface FirebaseAppCheckDataSourceContract {
    fun activate(callback: (FirebaseAppCheckResult) -> Unit)
}