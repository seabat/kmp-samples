package dev.seabat.kmp.firebasestorage.datasource

import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult

// NOTE: iOS 側の implements オブジェクトは iosMain ではなく iosApp にある
interface FirebaseStorageDataSourceContract {
    fun fetch(callback: (FirebaseStorageResult) -> Unit)
}