package dev.seabat.kmp.firebasestorage.datasource

sealed class FirebaseStorageError(message: String) : Throwable(message) {
    class NetworkError(message: String) : FirebaseStorageError(message)
    class DataParseError(message: String) : FirebaseStorageError(message)
} 