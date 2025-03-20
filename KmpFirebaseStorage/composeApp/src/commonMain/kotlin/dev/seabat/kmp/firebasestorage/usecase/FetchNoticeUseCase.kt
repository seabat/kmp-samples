package dev.seabat.kmp.firebasestorage.usecase

import dev.seabat.kmp.firebasestorage.repository.NoticeRepository
import dev.seabat.kmp.firebasestorage.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    operator fun invoke(): Flow<String> {
        return flow {
            val notice = noticeRepository.fetch()
            log("notice: $notice")
            emit(notice)
        }
    }
}