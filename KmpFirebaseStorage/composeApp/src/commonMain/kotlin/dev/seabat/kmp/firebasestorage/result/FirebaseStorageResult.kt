package dev.seabat.kmp.firebasestorage.result

import dev.seabat.kmp.firebasestorage.error.KmpFirebaseStorageError

sealed class FirebaseStorageResult {
    data class Success(val notice: String) : FirebaseStorageResult()
    data class Error(val error: KmpFirebaseStorageError) : FirebaseStorageResult()
} 