package dev.seabat.kmp.firebasestorage.repository

import dev.seabat.kmp.firebasestorage.datasource.FirebaseStorageDataSourceContract
import dev.seabat.kmp.firebasestorage.util.log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoticeRepository : KoinComponent {
    fun fetch(): String {
        val dataSource: FirebaseStorageDataSourceContract by inject()
        val notice = dataSource.fetch()
        log(notice)
        return notice
    }
}