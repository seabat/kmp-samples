package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import dev.seabat.kmp.firebasestorage.util.log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NoticeRepository : KoinComponent {
    suspend fun fetch(): String {
        val dataSource: FirebaseStorageDataSourceContract by inject()
        return suspendCoroutine { continuation ->
            dataSource.fetch { notice, error ->
                notice?.let {
                    log(it)
                    continuation.resume(it)
                }
            }
        }
    }
}