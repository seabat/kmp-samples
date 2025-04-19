package dev.seabat.kmp.firebasestorage.datasource

sealed class FirebaseAppCheckError(message: String) : Throwable(message) {
    class ActivationError(message: String) : FirebaseAppCheckError(message)
} 