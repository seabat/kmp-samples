package dev.seabat.kmp.firebasestorage.datasource

// NOTE: iOS 側の implements オブジェクトは iosMain ではなく iosApp にある
interface FirebaseStorageDataSourceContract {
    fun fetch(): String
}