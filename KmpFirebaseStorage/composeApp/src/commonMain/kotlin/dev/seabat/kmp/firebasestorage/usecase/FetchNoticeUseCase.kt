package dev.seabat.kmp.firebasestorage.usecase

import dev.seabat.kmp.firebasestorage.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    operator fun invoke(): Flow<String> {
        return flow {
            val notice = noticeRepository.fetch()
            emit(notice)
        }
    }
}