package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSource

class NoticeRepository(
    private val firebaseStorageDataSource: FirebaseStorageDataSource
) {
    fun fetch(): String {
        return firebaseStorageDataSource.fetch()
    }
}