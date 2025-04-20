package dev.seabat.kmp.firebasestorage.result

sealed class FirebaseStorageResult {
    data class Success(val data: String) : FirebaseStorageResult()
    data class Error(val throwable: Throwable) : FirebaseStorageResult()
} 