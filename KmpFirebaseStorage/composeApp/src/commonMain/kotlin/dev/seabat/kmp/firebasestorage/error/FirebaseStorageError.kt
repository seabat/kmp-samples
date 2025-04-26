package dev.seabat.kmp.firebasestorage.error

sealed class KmpFirebaseStorageError(originalMessage: String) : Throwable(originalMessage) {
    class FirebaseStorageFailure(originalMessage: String) : KmpFirebaseStorageError(originalMessage)
    class FirebaseStorageDataParse(originalMessage: String) : KmpFirebaseStorageError(originalMessage)
    class AppCheckActivationFailure(originalMessage: String) : KmpFirebaseStorageError(originalMessage)
}