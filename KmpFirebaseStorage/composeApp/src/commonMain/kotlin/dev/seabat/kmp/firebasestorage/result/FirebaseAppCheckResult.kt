package dev.seabat.kmp.firebasestorage.result

import dev.seabat.kmp.firebasestorage.error.KmpFirebaseStorageError

sealed class FirebaseAppCheckResult {
    data object Success : FirebaseAppCheckResult()
    data class Error(val error: KmpFirebaseStorageError) : FirebaseAppCheckResult()
}