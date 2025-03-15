package dev.seabat.kmp.rtdb.usecase

import dev.seabat.kmp.rtdb.repository.GuidRepository
import kotlinx.coroutines.flow.flow

class SaveGuidUseCase(
    private val guidRepository: GuidRepository,
) {
    operator fun invoke(guid: String) = flow {
        guidRepository.save(guid)
        emit(true)
    }
}