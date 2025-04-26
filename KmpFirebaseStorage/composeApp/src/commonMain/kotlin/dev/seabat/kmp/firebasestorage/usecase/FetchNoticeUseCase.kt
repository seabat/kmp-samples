package dev.seabat.kmp.firebasestorage.usecase

import dev.seabat.kmp.firebasestorage.repository.NoticeRepository
import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    operator fun invoke(): Flow<FirebaseStorageResult> {
        return flow {
            val result = noticeRepository.fetch()
            emit(result)
        }
    }
}