package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoticeRepository : KoinComponent {
    suspend fun fetch(): FirebaseStorageResult {
        val dataSource: FirebaseStorageDataSourceContract by inject()
        try {
            return dataSource.fetch()
        } catch (e: Throwable) {
            // NOTE: continuation.resume(throwing: ) でスローされた NSError はここで受け取る
            println("NoticeRepository.fetch: ${e.message}")
            throw e
        }
    }
}